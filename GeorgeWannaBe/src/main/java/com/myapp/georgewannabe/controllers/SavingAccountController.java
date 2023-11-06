package com.myapp.georgewannabe.controllers;

import com.myapp.georgewannabe.dtos.AccountInDTO;
import com.myapp.georgewannabe.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class SavingAccountController {
    private final AccountService accountService;

    @Autowired
    public SavingAccountController(@Qualifier("savingAccountService") AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createSavingAccount")
    public Long createSavingAccount(@RequestBody AccountInDTO accountInDTO) {
        return accountService.createAccount(accountInDTO);
    }
}
