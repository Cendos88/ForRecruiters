package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.TransactionDtoIn;
import com.myapp.georgewannabe.models.Account;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.models.Transaction;
import com.myapp.georgewannabe.models.User;
import com.myapp.georgewannabe.repositories.AccountRepository;
import com.myapp.georgewannabe.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public Long createTransaction(TransactionDtoIn transactionDtoIn) throws GeorgeException {
        if (notOwnerOfAccount(transactionDtoIn.getSenderAccountId())) {
            throw new GeorgeException("You are not the owner of this account");
        }
        Transaction newTransaction = Transaction.builder()
                .senderAccountId(transactionDtoIn.getSenderAccountId())
                .receiverAccountId(transactionDtoIn.getReceiverAccountId())
                .amount(transactionDtoIn.getAmount())
                .description(transactionDtoIn.getDescription())
                .date(LocalDate.now().toString())
                .build();
        Account senderAccount = accountRepository.findById(transactionDtoIn.getSenderAccountId()).get();
        senderAccount.setBalance(senderAccount.getBalance() - transactionDtoIn.getAmount());
        accountRepository.save(senderAccount);
        Account receiverAccount = accountRepository.findById(transactionDtoIn.getReceiverAccountId()).get();
        receiverAccount.setBalance(receiverAccount.getBalance() + transactionDtoIn.getAmount());
        accountRepository.save(receiverAccount);
        transactionRepository.save(newTransaction);
        return newTransaction.getId();
    }

    @Override
    public Transaction getTransaction(Long transactionId) throws GeorgeException {
        Transaction transaction =transactionRepository.findById(transactionId).get();
        if(notOwnerOfAccount(transaction.getSenderAccountId()) && notOwnerOfAccount(transaction.getReceiverAccountId())){
            throw new GeorgeException("Dont look into others accounts");
        }
        return transaction;
    }


    @Override
    public List<Transaction> getAllTransactionsByAccount(Long accountId) throws GeorgeException {
        if (notOwnerOfAccount(accountId)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        return transactionRepository.findAllBySenderAccountIdOrReceiverAccountId(accountId);
    }

    @Override
    public List<Transaction> getAllTransactionsByTwoAccounts(Long senderId, Long receiverId) throws GeorgeException {
        if (notOwnerOfAccount(senderId) && notOwnerOfAccount(receiverId)) {
            throw new GeorgeException("Dont look into others accounts");
        }
        return transactionRepository.findAllBySenderAccountIdAndReceiverAccountId(senderId, receiverId);
    }

    @Override
    public List<Transaction> getAllTransactionsByDate(Long accountId, String date) throws GeorgeException {
        if (notOwnerOfAccount(accountId)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByAmount(Double amount, Long accountId) throws GeorgeException {
        if (notOwnerOfAccount(accountId)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        return null;
    }

    private static boolean notOwnerOfAccount(Long accountId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return !user.getId().equals(accountId);
    }
}
