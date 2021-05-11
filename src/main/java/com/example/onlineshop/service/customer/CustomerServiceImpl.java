package com.example.onlineshop.service.customer;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
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
    public Customer saveCustomer(Customer customer) {
        Example<Customer> customerExample = Example.of(customer);
        if (this.customerRepository.exists(customerExample)){
            throw new EntityExistsException("Customer [" + customer.getFirstName() + " " + customer.getLastName() +
                    "] already exists");
        }
        return this.customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerById(Long customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [" + customerId + "] not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Customer> findAllCustomers() {
        Collection<Customer> collection = this.customerRepository.findAll();
        if (collection.isEmpty()){
            throw new ResourceNotFoundException("Customer collection is empty");
        }
        return collection;
    }

    @Override
    public void updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id [" + customerId + "] not found"));
        if (customer.equals(updatedCustomer)) {
            return;
        }
        customer.setLastName(updatedCustomer.getLastName());
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setGender(updatedCustomer.getGender());
        customer.setShippingAddress(updatedCustomer.getShippingAddress());
        customer.setBirthDate(updatedCustomer.getBirthDate());
        this.customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomerById(Long customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with id [" + customerId + "] not found");
        }
        this.customerRepository.delete(customer.get());
    }

}
