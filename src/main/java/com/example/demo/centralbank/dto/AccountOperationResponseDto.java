package com.example.demo.centralbank.dto;



import com.example.demo.centralbank.enums.OperationType;
import com.example.demo.centralbank.models.BankAccount;
import lombok.Data;


import java.time.LocalDate;

@Data
public class AccountOperationResponseDto {
    private Long id;
    private LocalDate operationDate;
    private double amount;
    private OperationType type;
    private BankAccount secondBankAccount;

}
