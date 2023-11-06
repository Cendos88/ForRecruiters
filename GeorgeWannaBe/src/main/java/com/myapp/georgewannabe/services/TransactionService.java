package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.TransactionDtoIn;
import com.myapp.georgewannabe.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    public Long createTransaction(TransactionDtoIn transactionDtoIn);

    public Transaction getTransaction(Long transactionId);

    public List<Transaction> getAllTransactionsByAccount(Long accountId);

    public List<Transaction> getAllTransactionsByTwoAccounts(Long senderId, Long receiverId);

    public List<Transaction> getAllTransactionsByDate(Long accountId, String date);

    public List<Transaction> getAllTransactionsByAmount(Double amount);


}
