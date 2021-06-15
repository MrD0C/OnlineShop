package com.example.onlineshop.service.transaction;

import com.example.onlineshop.model.transaction.Transaction;

import java.math.BigDecimal;
import java.util.Collection;

public interface TransactionService {

    Collection<Transaction> getCustomerTransactions(Long id);

    void doTransactionDeposit(Long customerId, BigDecimal amount);

    void doTransactionOnline(Long customerId,BigDecimal amount);
}
