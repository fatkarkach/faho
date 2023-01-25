package com.example.demo.centralbank.dto;

import com.example.demo.centralbank.enums.AccountStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BankAccountDto {

    private Long accountNumber;
    private double balance;
    private LocalDate creationDate;
    private AccountStatus status;

    private UserDto userDto;

}
