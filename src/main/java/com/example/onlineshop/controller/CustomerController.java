package com.example.onlineshop.controller;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController implements IRestController<Customer, Long> {

    private final IService<Customer, Long> service;

    @Autowired
    public CustomerController(IService<Customer, Long> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Collection<Customer>> findAll() {
        Collection<Customer> customerCollection = this.service.findAll();
        return new ResponseEntity<>(customerCollection, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> findById(Long id) {
        Customer requestedCustomer = this.service.findById(id);
        return new ResponseEntity<>(requestedCustomer, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Customer> save(Customer customer) {
        Customer savedCustomer = this.service.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, Customer customer) {
        this.service.update(id, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
