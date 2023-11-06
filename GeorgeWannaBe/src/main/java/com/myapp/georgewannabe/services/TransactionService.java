package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.TransactionDtoIn;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    public Long createTransaction(TransactionDtoIn transactionDtoIn) throws GeorgeException;

    public Transaction getTransaction(Long transactionId) throws GeorgeException;

    public List<Transaction> getAllTransactionsByAccount(Long accountId) throws GeorgeException;

    public List<Transaction> getAllTransactionsByTwoAccounts(Long senderId, Long receiverId) throws GeorgeException;

    public List<Transaction> getAllTransactionsByDate(Long accountId, String date) throws GeorgeException;

    public List<Transaction> getAllTransactionsByAmount(Double amount, Long accountId) throws GeorgeException;


}
