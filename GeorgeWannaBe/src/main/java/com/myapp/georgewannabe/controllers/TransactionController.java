package com.myapp.georgewannabe.controllers;

import com.myapp.georgewannabe.dtos.TransactionDTOIn;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransactionDTOIn transactionDTOIn){
        try {
            return ResponseEntity.status(200).body(transactionService.createTransaction(transactionDTOIn));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
