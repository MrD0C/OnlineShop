package com.example.onlineshop.controller;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Gender;
import com.example.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public Collection<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable Long id){
        return this.customerService.findCustomerById(id);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteCustomerById(@PathVariable Long id){
        this.customerService.deleteCustomerById(id);
    }

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer){
        return this.customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}/update")
    public void updateCustomer(@PathVariable Long id, @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) LocalDate birthDate,
                               @RequestParam(required = false) String shippingAddress,
                               @RequestParam(required = false) Gender gender){
        this.customerService.updateCustomer(id,firstName,lastName,birthDate,shippingAddress,gender);
    }
}
