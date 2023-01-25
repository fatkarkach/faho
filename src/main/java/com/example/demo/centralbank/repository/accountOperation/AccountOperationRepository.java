package com.example.demo.centralbank.repository.accountOperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.centralbank.models.AccountOperation;
import com.example.demo.centralbank.models.BankAccount;
import java.util.List;

@Repository
public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

    public List<AccountOperation> findByBankAccount(BankAccount bankAccount);
}
