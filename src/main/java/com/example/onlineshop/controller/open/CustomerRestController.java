package com.example.onlineshop.controller.open;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerRestController implements IRestController<Customer, Long> {

    private final IService<Customer, Long> service;

    @Autowired
    public CustomerRestController(IService<Customer, Long> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Collection<Customer>> findAll() {
        Collection<Customer> collection = this.service.findAll();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> findById(Long id) {
        Customer entity = this.service.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> save(Customer entity) {
        Customer savedEntity = this.service.save(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, Customer entity) {
        this.service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
