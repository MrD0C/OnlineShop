package com.example.onlineshop.service.customer;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.transaction.Transaction;

import java.math.BigDecimal;
import java.util.Collection;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Long id);

    void updateCustomer(Long id, Customer customer);

    void deleteCustomerById(Long id);

    Collection<Customer> findAllCustomers();

    void doTransaction(Long customerId, BigDecimal amount);

    Collection<Transaction> getCustomerTransactions(Long id);

}
