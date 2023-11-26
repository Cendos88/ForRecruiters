package com.myapp.georgewannabe.controllers;

import com.myapp.georgewannabe.dtos.AccountInDTO;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})

public class SavingAccountController {
    private final AccountService accountService;

    @Autowired
    public SavingAccountController(@Qualifier("savingAccountService") AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/savingAccounts")
    public ResponseEntity<?> getSavingAccounts() {
        return ResponseEntity.status(200).body(accountService.getAccounts());
    }

    @PostMapping("/createSavingAccount")
    public ResponseEntity<?> createSavingAccount(@RequestBody AccountInDTO accountInDTO) {
        return ResponseEntity.status(200).body(accountService.createAccount(accountInDTO));
    }

    @DeleteMapping("/deleteSavingAccount/{id}")
    public ResponseEntity<?> deleteSavingAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(accountService.deleteAccount(id));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/getSavingBalance")
    public ResponseEntity<?> getSavingBalance(@RequestHeader Long id) {
        try {
            return ResponseEntity.status(200).body(accountService.getBalance(id));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
