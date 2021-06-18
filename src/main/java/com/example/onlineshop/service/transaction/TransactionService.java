package com.example.onlineshop.service.transaction;

import com.example.onlineshop.model.transaction.Transaction;
import com.example.onlineshop.model.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.Collection;

public interface TransactionService {

    Collection<Transaction> findCustomerTransactions(Long customerId);

    Transaction doTransaction(Long customerId, BigDecimal amount, TransactionType type);

}
