package com.example.onlineshop.repository;

import com.example.onlineshop.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,UUID> {

    Collection<Transaction> getAllByCustomerId(Long customerId);
}
