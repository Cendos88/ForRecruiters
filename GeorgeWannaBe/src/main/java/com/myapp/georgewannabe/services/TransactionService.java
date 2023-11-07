package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.TransactionDTOIn;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Long createTransaction(TransactionDTOIn transactionDtoIn) throws GeorgeException;

    Transaction getTransaction(Long transactionId) throws GeorgeException;

    List<Transaction> getAllTransactionsByAccount(Long accountId) throws GeorgeException;

    List<Transaction> getAllTransactionsByTwoAccounts(Long senderId, Long receiverId) throws GeorgeException;

    List<Transaction> getAllTransactionsByDate(Long accountId, String date) throws GeorgeException;

    List<Transaction> getAllTransactionsByAmount(Double amount, Long accountId) throws GeorgeException;


}
