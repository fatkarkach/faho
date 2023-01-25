package com.example.demo.centralbank.controllers;



import com.example.demo.centralbank.reqObjects.Response;
import com.example.demo.centralbank.reqObjects.Transaction;
import com.example.demo.centralbank.services.client.ClientService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import com.example.demo.centralbank.reqObjects.RegisterForm;
import com.example.demo.centralbank.reqObjects.Response;
import com.example.demo.centralbank.reqObjects.Transaction;
import com.example.demo.centralbank.services.client.ClientService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/register",method =RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@ModelAttribute RegisterForm registerForm) throws IOException {
        this.clientService.register(registerForm);
        return new ResponseEntity<>(Response.builder().error("No error is there").value("votre compte bancaire a été créé").build(), HttpStatusCode.valueOf(200));
    }


    public ResponseEntity<Response> makeTransaction(@RequestBody Transaction transaction, Principal principal){

        Boolean res = this.clientService.makeTransaction(principal.getName(), transaction.getIdReceiverBankAccount(), transaction.getAmount());
        if(res){
            return new ResponseEntity<>(Response.builder().error("No error is there").value("Votre transaction a été bien réalisée").build(),HttpStatusCode.valueOf(200));
        }else{
            return new ResponseEntity<>(Response.builder().error("sending bad data").value("Votre transaction n'a pas été bien effectuée ").build(),HttpStatusCode.valueOf(400));
        }
    }


}
