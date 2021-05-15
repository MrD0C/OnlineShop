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
    public void deleteCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        this.customerRepository.delete(customer);
    }

}
