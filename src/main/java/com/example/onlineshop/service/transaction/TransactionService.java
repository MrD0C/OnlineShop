package com.example.onlineshop.service.transaction;

import com.example.onlineshop.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public interface TransactionService {

    Collection<Transaction> getCustomerTransactions(Long id);

    void doTransactionDeposit(Long customerId, BigDecimal amount);

    void doTransactionOnline(Long customerId,BigDecimal amount);
}
