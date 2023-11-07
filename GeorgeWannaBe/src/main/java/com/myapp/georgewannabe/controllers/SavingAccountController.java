package com.myapp.georgewannabe.controllers;

import com.myapp.georgewannabe.dtos.AccountInDTO;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class SavingAccountController {
    private final AccountService accountService;

    @Autowired
    public SavingAccountController(@Qualifier("savingAccountService") AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createSavingAccount")
    public ResponseEntity<?> createSavingAccount(@RequestBody AccountInDTO accountInDTO) {
        return ResponseEntity.status(200).body(accountService.createAccount(accountInDTO));
    }
    @PostMapping("/deleteSavingAccount")
    public ResponseEntity<?> deleteSavingAccount(@RequestBody Long id) {
        try {
            return ResponseEntity.status(200).body(accountService.deleteAccount(id));
        }catch (GeorgeException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping("/getSavingBalance")
    public ResponseEntity<?> getSavingBalance(@RequestHeader Long id) {
        try {
            return ResponseEntity.status(200).body(accountService.getBalance(id));
        }catch (GeorgeException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
