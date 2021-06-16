package com.example.onlineshop.controller;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.transaction.Transaction;
import com.example.onlineshop.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController implements IRestController<Customer, Long> {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Customer>> findAll() {
        Collection<Customer> customerCollection = this.customerService.findAllCustomers();
        return new ResponseEntity<>(customerCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@Min(value = 1, message = "ID must be equal or greater than 1")
                                             @PathVariable Long id) {
        Customer requestedCustomer = this.customerService.findCustomerById(id);
        return new ResponseEntity<>(requestedCustomer, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Min(value = 1, message = "ID must be equal or greater than 1")
                                           @PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = this.customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Min(value = 1, message = "ID must be equal or greater than 1")
                                       @PathVariable Long id,
                                       @Valid @RequestBody Customer customer) {
        this.customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Todo Перенести в TransactionController
    @PostMapping("/{id}/transactions/{amount}")
    public ResponseEntity<Void> doTransaction(@Min(value = 1, message = "ID must be equal or greater than 1")
                                              @PathVariable Long id,
                                              @PathVariable BigDecimal amount) {
        this.customerService.doTransaction(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Todo Перенести в TransactionController
    @GetMapping("/{id}/transactions")
    public ResponseEntity<Collection<Transaction>> getTransactions(@Min(value = 1, message = "ID must be equal or greater than 1")
                                                                   @PathVariable Long id) {
        Collection<Transaction> transactionCollection = this.customerService.getCustomerTransactions(id);
        return new ResponseEntity<>(transactionCollection, HttpStatus.OK);
    }
}
