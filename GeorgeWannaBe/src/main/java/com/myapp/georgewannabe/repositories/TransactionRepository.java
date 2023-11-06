package com.myapp.georgewannabe.repositories;

import com.myapp.georgewannabe.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllBySenderAccountIdOrReceiverAccountId(Long accountId, Long receiverId);
    List<Transaction> findAllBySenderAccountIdAndReceiverAccountId(Long senderId, Long receiverId);
}
