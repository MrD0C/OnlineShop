package com.example.onlineshop.controller;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
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
    public Collection<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@Min(value = 1,message = "ID must be equal or greater than 1") @PathVariable Long id){
        return this.customerService.findCustomerById(id);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteCustomerById(@Min(value = 1,message = "ID must be equal or greater than 1") @PathVariable Long id){

        this.customerService.deleteCustomerById(id);
    }

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer){
        return this.customerService.saveCustomer(customer);
    }

    //TODO remove request params and set class
    @PutMapping("/{id}/update")
    @Validated
    public void updateCustomer(@PathVariable Long id,
                               @Pattern(regexp = "^[А-Я][а-я]+$") @RequestParam(required = false) String firstName,
                               @Pattern(regexp = "^[А-Я][а-я]+$") @RequestParam(required = false) String lastName,
                               @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate birthDate,
                               @Pattern(regexp = "^(ул.)[А-Я][а-я|\\s]*(,д.)[1-9]{1,3}(,кв.)[1-9]{1,3}$") @RequestParam(required = false) String shippingAddress,
                               @Pattern(regexp = "^[А-Я][а-я]*$") @RequestParam(required = false) String gender){
        this.customerService.updateCustomer(id,firstName,lastName,birthDate,shippingAddress,gender);
    }
}
