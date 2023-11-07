package com.myapp.georgewannabe.controllers;

import com.myapp.georgewannabe.dtos.TransactionDTOIn;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransactionDTOIn transactionDTOIn) {
        try {
            return ResponseEntity.status(200).body(transactionService.createTransaction(transactionDTOIn));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<?> getTransaction(@PathVariable Long transactionId) {
        try {
            return ResponseEntity.status(200).body(transactionService.getTransaction(transactionId));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<?> getAllTransactionsByAccount(@PathVariable Long accountId) {
        try {
            return ResponseEntity.status(200).body(transactionService.getAllTransactionsByAccount(accountId));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/transactions/{senderId}/{receiverId}")
    public ResponseEntity<?> getAllTransactionsByTwoAccounts(@PathVariable Long senderId, @PathVariable Long receiverId) {
        try {
            return ResponseEntity.status(200).body(transactionService.getAllTransactionsByTwoAccounts(senderId, receiverId));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/transactions")//date format: yyyy-MM-dd
    public ResponseEntity<?> getAllTransactionsByDate(@RequestParam Long accountId, @RequestParam String date) {
        try {
            return ResponseEntity.status(200).body(transactionService.getAllTransactionsByDate(accountId, date));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/transactions/amount/{accountId}")
    public ResponseEntity<?> getAllTransactionsByAmount(@RequestParam Double amount, @PathVariable Long accountId) {
        try {
            return ResponseEntity.status(200).body(transactionService.getAllTransactionsByAmount(amount, accountId));
        } catch (GeorgeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
