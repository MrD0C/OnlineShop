package com.example.onlineshop.service.customer;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Transaction;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.math.BigDecimal;
import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TransactionService transactionService) {
        this.customerRepository = customerRepository;
        this.transactionService = transactionService;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Example<Customer> customerExample = Example.of(customer);
        if (this.customerRepository.exists(customerExample)){
            throw new EntityExistsException("Customer [" + customer.getFirstName() + " " + customer.getLastName() +
                    "] already exists");
        }
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [" + id + "] not found"));
    }

    @Override
    public Collection<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public void updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = findCustomerById(id);
        if (!customer.equals(updatedCustomer)) {
            customer.setLastName(updatedCustomer.getLastName());
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setGender(updatedCustomer.getGender());
            customer.setShippingAddress(updatedCustomer.getShippingAddress());
            customer.setBirthDate(updatedCustomer.getBirthDate());
            this.customerRepository.save(customer);
        }
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        this.customerRepository.delete(customer);
    }

    @Override
    public void doTransaction(Long id,BigDecimal amount){
        Customer customer = findCustomerById(id);
        if (amount.intValue() >= 0) {
            customer.setBalance(customer.getBalance().add(amount));
            this.transactionService.doTransactionDeposit(id,amount);
        } else {
            if (customer.getBalance().add(amount).intValue() < 0){
                throw new IllegalArgumentException("Not enough money on balance");
            }
            customer.setBalance(customer.getBalance().add(amount));
            this.transactionService.doTransactionOnline(id,amount);
        }
    }

    @Override
    public Collection<Transaction> getCustomerTransactions(Long id) {
        return this.transactionService.getCustomerTransactions(id);
    }
}
