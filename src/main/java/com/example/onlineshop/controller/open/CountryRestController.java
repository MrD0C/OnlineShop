package com.example.onlineshop.controller.open;

import com.example.onlineshop.model.Country;
import com.example.onlineshop.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryRestController implements IRestController<Country,Long> {

    private final IService<Country,Long> service;

    @Autowired
    public CountryRestController(IService<Country, Long> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Collection<Country>> findAll() {
        Collection<Country> collection = this.service.findAll();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Country> findById(Long id) {
        Country entity = this.service.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Country> save(Country entity) {
        Country savedEntity = this.service.save(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, Country entity) {
        this.service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
