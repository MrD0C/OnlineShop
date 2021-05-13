package com.example.onlineshop.service.customer;

import com.example.onlineshop.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Long id);

    void updateCustomer(Long id, Customer customer);

    void deleteCustomerById(Long id);

    Collection<Customer> findAllCustomers();

}
