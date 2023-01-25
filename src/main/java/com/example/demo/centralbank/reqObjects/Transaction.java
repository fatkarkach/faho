package com.example.demo.centralbank.reqObjects;

import lombok.Data;

@Data
public class Transaction {
    Long idSenderBankAccount;
    Long idReceiverBankAccount;
    double amount;
}
