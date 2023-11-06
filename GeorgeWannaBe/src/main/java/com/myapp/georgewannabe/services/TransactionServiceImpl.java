package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.TransactionDtoIn;
import com.myapp.georgewannabe.models.Account;
import com.myapp.georgewannabe.models.Transaction;
import com.myapp.georgewannabe.repositories.AccountRepository;
import com.myapp.georgewannabe.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    @Override
    public Long createTransaction(TransactionDtoIn transactionDtoIn) {
        Transaction newTransaction = Transaction.builder()
                .senderAccountId(transactionDtoIn.getSenderAccountId())
                .receiverAccountId(transactionDtoIn.getReceiverAccountId())
                .amount(transactionDtoIn.getAmount())
                .description(transactionDtoIn.getDescription())
                .date(LocalDate.now().toString())
                .build();
        Account senderAccount = accountRepository.findById(transactionDtoIn.getSenderAccountId()).get();
        senderAccount.setBalance(senderAccount.getBalance()-transactionDtoIn.getAmount());
        accountRepository.save(senderAccount);
        Account receiverAccount = accountRepository.findById(transactionDtoIn.getReceiverAccountId()).get();
        receiverAccount.setBalance(receiverAccount.getBalance()+transactionDtoIn.getAmount());
        accountRepository.save(receiverAccount);
        transactionRepository.save(newTransaction);
        return newTransaction.getId();
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId).get();
    }

    @Override
    public List<Transaction> getAllTransactionsByAccount(Long accountId) {
        return transactionRepository.findAllBySenderAccountIdOrReceiverAccountId(accountId);
    }

    @Override
    public List<Transaction> getAllTransactionsByTwoAccounts(Long senderId, Long receiverId) {
        return transactionRepository.findAllBySenderAccountIdAndReceiverAccountId(senderId,receiverId);
    }

    @Override
    public List<Transaction> getAllTransactionsByDate(Long accountId, String date) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByAmount(Double amount) {
        return null;
    }
}
