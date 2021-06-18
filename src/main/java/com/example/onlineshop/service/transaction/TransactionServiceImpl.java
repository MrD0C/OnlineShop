package com.example.onlineshop.service.transaction;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.transaction.Transaction;
import com.example.onlineshop.model.transaction.TransactionType;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Collection<Transaction> findCustomerTransactions(Long customerId) {
        return this.transactionRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Transaction doTransaction(Long customerId, BigDecimal amount,TransactionType type){
        Customer customer = this.customerRepository.getOne(customerId);
        Transaction transaction = new Transaction(amount,type,customer);
        return this.transactionRepository.save(transaction);
    }
}
