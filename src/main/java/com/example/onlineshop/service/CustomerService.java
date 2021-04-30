package com.example.onlineshop.service;

import com.example.onlineshop.model.Customer;

import java.util.Collection;


public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Long customerId);

    void updateCustomer(Long id, Customer customer);

    void deleteCustomerById(Long customerId);

    Collection<Customer> findAllCustomers();

}
