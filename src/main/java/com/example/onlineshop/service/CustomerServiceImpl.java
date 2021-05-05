package com.example.onlineshop.service;

import com.example.onlineshop.exception.customer.CustomerAlreadyExistException;
import com.example.onlineshop.exception.customer.CustomerNotFoundException;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        Example<Customer> customerExample = Example.of(customer);
        if (this.customerRepository.exists(customerExample)){
            throw new CustomerAlreadyExistException(customer.getLastName(),customer.getFirstName());
        }
        return this.customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerById(Long customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
        return customer.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    @Transactional
    public void updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        if (customer.equals(updatedCustomer)) {
            return;
        }
        customer.setLastName(updatedCustomer.getLastName());
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setGender(updatedCustomer.getGender());
        customer.setShippingAddress(updatedCustomer.getShippingAddress());
        customer.setBirthDate(updatedCustomer.getBirthDate());
    }

    @Override
    @Transactional
    public void deleteCustomerById(Long customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
        this.customerRepository.delete(customer.get());
    }

}
