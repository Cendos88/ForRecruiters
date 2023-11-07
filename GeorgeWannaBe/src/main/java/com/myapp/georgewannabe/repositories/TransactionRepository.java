package com.myapp.georgewannabe.repositories;

import com.myapp.georgewannabe.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllBySenderAccountIdOrReceiverAccountId(Long accountId, Long receiverId);
    List<Transaction> findAllBySenderAccountIdAndReceiverAccountId(Long senderId, Long reiverId);
    List<Transaction> findAllBySenderAccountId(Long accountId);
    List<Transaction> findAllBySenderAccountIdAndDate(Long accountId, String date);
    List<Transaction> findAllBySenderAccountIdAndAmount(Long accountId, Double amount);


}
