package com.example.onlineshop.service;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;

@Service
public class CustomerService implements IService<Customer, Long> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Collection<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [" + id + "] not found"));
    }

    @Override
    public Customer save(Customer customer) {
        Example<Customer> customerExample = Example.of(customer);
        if (this.customerRepository.exists(customerExample)) {
            throw new EntityExistsException("Customer [" + customer.getFirstName() + " " + customer.getLastName() +
                    "] already exists");
        }
        return this.customerRepository.save(customer);
    }

    @Override
    public void update(Long id, Customer updatedCustomer) {
        Customer customer = findById(id);
        if (!customer.equals(updatedCustomer)) {
            customer.setLastName(updatedCustomer.getLastName());
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setGender(updatedCustomer.getGender());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setBirthDate(updatedCustomer.getBirthDate());
            this.customerRepository.save(customer);
        }
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = findById(id);
        this.customerRepository.delete(customer);
    }
}
