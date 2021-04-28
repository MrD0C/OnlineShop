package com.example.onlineshop.service;

import com.example.onlineshop.model.Customer;

import java.time.LocalDate;
import java.util.Collection;


public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer findCustomerById(Long customerId);
    void updateCustomer(Long customerId, String firstName, String lastName,
                   LocalDate birthDate, String shippingAddress, String gender);
    void deleteCustomerById(Long customerId);
    Collection<Customer> getAllCustomers();

}
