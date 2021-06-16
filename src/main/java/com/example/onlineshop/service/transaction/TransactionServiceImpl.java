package com.example.onlineshop.service.transaction;

import com.example.onlineshop.model.transaction.Transaction;
import com.example.onlineshop.model.transaction.TransactionType;
import com.example.onlineshop.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Collection<Transaction> getTransactionsByCustomerId(Long customerId) {
        return this.transactionRepository.getAllByCustomerId(customerId);
    }

    @Override
    public void doTransactionDeposit(Long customerId, BigDecimal amount) {
        this.transactionRepository.save(new Transaction(amount, TransactionType.DEPOSIT, customerId));
    }

    @Override
    public void doTransactionOnline(Long customerId, BigDecimal amount) {
        this.transactionRepository.save(new Transaction(amount, TransactionType.ONLINE, customerId));
    }
}
