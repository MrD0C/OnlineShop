package com.example.onlineshop.controller;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Customer>> getAllCustomers() {
        Collection<Customer> collection = this.customerService.findAllCustomers();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@Min(value = 1, message = "ID must be equal or greater than 1")
                                                         @PathVariable Long id) {
        Customer foundedCustomer = this.customerService.findCustomerById(id);
        return new ResponseEntity<>(foundedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@Min(value = 1, message = "ID must be equal or greater than 1")
                                                       @PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = this.customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@Min(value = 1, message = "ID must be equal or greater than 1")
                                                   @PathVariable Long id,
                                                   @Valid @RequestBody Customer customer) {
        this.customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
