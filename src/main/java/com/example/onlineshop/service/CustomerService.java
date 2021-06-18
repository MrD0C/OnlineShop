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

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Customer> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [" + id + "] not found"));
    }

    @Override
    public Customer save(Customer entity) {
        Example<Customer> example = Example.of(entity);
        if (this.repository.exists(example)) {
            throw new EntityExistsException("Customer [" + entity.getFirstName() + " " + entity.getLastName() +
                    "] already exists");
        }
        return this.repository.save(entity);
    }

    @Override
    public void update(Long id, Customer entity) {
        Customer customer = findById(id);
        if (!customer.equals(entity)) {
            customer.setLastName(entity.getLastName());
            customer.setFirstName(entity.getFirstName());
            customer.setGender(entity.getGender());
            customer.setAddress(entity.getAddress());
            customer.setBirthDate(entity.getBirthDate());
            this.repository.save(customer);
        }
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = findById(id);
        this.repository.delete(customer);
    }
}
