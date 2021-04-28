package com.example.onlineshop.service;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Gender;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;


public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer findCustomerById(Long customerId);
    void updateCustomer(Long customerId, String firstName, String lastName,
                   LocalDate birthDate, String shippingAddress, Gender gender);
    void deleteCustomerById(Long customerId);
    Collection<Customer> getAllCustomers();

}
