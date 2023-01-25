package com.example.demo.centralbank.services.client;
import com.example.demo.centralbank.dto.AccountOperationResponseDto;
import com.example.demo.centralbank.dto.BankAccountDto;
import com.example.demo.centralbank.models.BankAccount;
import com.example.demo.centralbank.models.Cin;
import com.example.demo.centralbank.models.User;
import com.example.demo.centralbank.reqObjects.RegisterForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClientService {


    User addClient(User user);
    BankAccount createBankAccount(BankAccount bankAccount);
    Cin addCin(MultipartFile file, User user) throws IOException;

    void register(RegisterForm registerForm) throws IOException;

    boolean makeTransaction(String emailSenderBankAccount, Long idReceiverBankAccount, double amount);

    boolean makeTransfer(Long idBankAccount, double amount);

    User getUserByEmail(String email);

    BankAccountDto getBankAccount(String email);

    List<AccountOperationResponseDto> getClientOperations(String email);

}
