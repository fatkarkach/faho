package com.example.demo.centralbank.controllers;
import com.example.demo.centralbank.dto.AccountOperationRequestDto;
import com.example.demo.centralbank.services.client.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class TransferController {

    private ClientService clientService;

    public TransferController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Map> makeTransfer(@RequestBody AccountOperationRequestDto operationRequestDto){
        boolean transfer = this.clientService.makeTransfer(operationRequestDto.getBankAccountId(), operationRequestDto.getAmount());
        if(transfer){
            return ResponseEntity.accepted().body(Map.of("message","Your operation has been pasted"));
        }
        return ResponseEntity.badRequest().body(Map.of("message","Your operation has not been pasted"));
    }

    @PostMapping("/transfer/accounts")
    public ResponseEntity<Map> makeTransaction(@RequestBody AccountOperationRequestDto operationRequestDto, Principal principal){
        boolean transaction = this.clientService.makeTransaction(principal.getName(), operationRequestDto.getSecondBankAccountId(), operationRequestDto.getAmount());
        if(transaction){
            return ResponseEntity.accepted().body(Map.of("message","Your operation has been pasted"));
        }
        return ResponseEntity.badRequest().body(Map.of("message","Your operation has not been pasted"));
    }

}
