package com.example.demo;
import com.example.demo.centralbank.models.BankAccount;
import com.example.demo.centralbank.models.Cin;
import com.example.demo.centralbank.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.centralbank.repository.bankAccount.BankAccountRepository;
import com.example.demo.centralbank.repository.cin.CinRepository;
import com.example.demo.centralbank.services.client.ClientService;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Bean @Transactional
    CommandLineRunner commandLineRunner(ClientService clientService, CinRepository cinRepository, BankAccountRepository bankAccountRepository){
        return args -> {
                        /*
                            User user = clientService.addClient(
                                    User.builder()
                                            .firstName("Agent")
                                            .lastName("Agent")
                                            .email("agent@gmail.com")
                                            .password("agent")
                                            .role("AGENT")
                                            .build()
                            );

                        Optional<Cin> cin = cinRepository.findById(1L);

                        BankAccount bankAccount = bankAccountRepository.save(BankAccount.builder().accountNumber(123456789L).balance(200.50)
                                .creationDate(LocalDate.from(LocalDateTime.now())).build());
                         */






        };
    }

}
