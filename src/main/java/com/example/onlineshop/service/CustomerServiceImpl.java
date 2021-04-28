package com.example.onlineshop.service;

import com.example.onlineshop.exception.customer.CustomerNotFoundException;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Gender;
import com.example.onlineshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer saveCustomer(Customer customer){
        return this.customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findCustomerById(Long customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()){
            throw new CustomerNotFoundException(customerId);
        }
        return customer.get();
    }

    public Collection<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Transactional
    public void updateCustomer(Long customerId, String firstName, String lastName,
                               LocalDate birthDate, String shippingAddress, String gender) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        if (isStringCorrect(firstName) && !Objects.equals(customer.getFirstName(),firstName)){
            customer.setFirstName(firstName);
        }
        if (isStringCorrect(lastName) && !Objects.equals(customer.getLastName(),lastName)){
            customer.setLastName(lastName);
        }
        if (birthDate != null && LocalDate.now().isAfter(birthDate)){
            customer.setBirthDate(birthDate);
        }
        if (isStringCorrect(shippingAddress) && !Objects.equals(customer.getShippingAddress(),shippingAddress)){
            customer.setShippingAddress(shippingAddress);
        }
        if (isStringCorrect(gender) && !customer.getGender().isSame(gender)){
            customer.setGender(Gender.valueOf(gender));
        }
    }

    private boolean isStringCorrect(String string){
        return string != null && string.length() > 0;
    }

    @Transactional
    public void deleteCustomerById(Long customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()){
            throw new CustomerNotFoundException(customerId);
        }
        customerRepository.delete(customer.get());
    }

}
