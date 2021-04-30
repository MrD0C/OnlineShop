package com.example.onlineshop.controller;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Collection<Customer> getAllCustomers() {
        return this.customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@Min(value = 1, message = "ID must be equal or greater than 1") @PathVariable Long id) {
        return this.customerService.findCustomerById(id);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteCustomerById(@Min(value = 1, message = "ID must be equal or greater than 1") @PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
    }

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return this.customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}/update")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        this.customerService.updateCustomer(id, customer);
    }
}
