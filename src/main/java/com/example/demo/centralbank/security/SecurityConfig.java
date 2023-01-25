package com.example.demo.centralbank.security;
import com.example.demo.centralbank.services.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   private UserService userService;
   private PasswordEncoder passwordEncoder;

   private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

   public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
      this.userService = userService;
      this.passwordEncoder = passwordEncoder;
      this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
   }

   @Bean
   UserDetailsService userDetailsService(){
      return email -> {
         com.example.demo.centralbank.models.User user = userService.loadUserByEmail(email);
         if (user == null) {
            throw new UsernameNotFoundException("No user found for "+ email + ".");
         }
         GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
         List<GrantedAuthority> authorities = Collections.singletonList(authority);
         return new User(user.getEmail(), user.getPassword(),authorities);

      };
   }


   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(csrf->csrf.disable());
      //http.exceptionHandling()
        //   .authenticationEntryPoint(this.customAuthenticationEntryPoint);
      http
              .authorizeHttpRequests(aut->aut.requestMatchers("/addCin/**").permitAll())
              .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
               .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .httpBasic(Customizer.withDefaults());
      http.addFilter(new JwtAuthenticationFilter(authenticationManager(this.authenticationProvider(this.userDetailsService()))));
      http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
      return http.build();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
      return new ProviderManager(Arrays.asList(authenticationProvider));
   }

   @Bean
   public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService);
      authenticationProvider.setPasswordEncoder(this.passwordEncoder);
      return authenticationProvider;
   }


   @Bean
   public CorsFilter corsFilter() {
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.setAllowCredentials(true);
      corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
      corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
              "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
              "Access-Control-Request-Method", "Access-Control-Request-Headers"));
      corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
              "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
      corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
      UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
      urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
      return new CorsFilter(urlBasedCorsConfigurationSource);
   }

}
