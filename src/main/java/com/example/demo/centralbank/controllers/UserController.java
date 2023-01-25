package com.example.demo.centralbank.controllers;
import com.example.demo.centralbank.dto.BankAccountDto;
import com.example.demo.centralbank.repository.bankAccount.BankAccountRepository;
import com.example.demo.centralbank.services.client.ClientService;
import com.example.demo.centralbank.services.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class UserController {

    private UserService userService;
    private ClientService clientService;
    private BankAccountRepository bankAccountRepository;


    public UserController(UserService userService, ClientService clientService, BankAccountRepository bankAccountRepository) {
        this.userService = userService;
        this.clientService = clientService;
        this.bankAccountRepository = bankAccountRepository;
    }


    @GetMapping(path = "/users")
    @PreAuthorize("hasAuthority('CLIENT')")
    public BankAccountDto appUsers(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        BankAccountDto bankAccount = this.clientService.getBankAccount(username);

        return bankAccount;
    }

}
