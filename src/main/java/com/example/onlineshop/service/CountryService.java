package com.example.onlineshop.service;

import com.example.onlineshop.model.Country;
import com.example.onlineshop.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;

@Service
public class CountryService implements IService<Country, Long> {

    private final CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Country> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id [" + id + "] not found"));
    }

    @Override
    public Country save(Country entity) {
        Example<Country> example = Example.of(entity);
        if (this.repository.exists(example)) {
            throw new EntityExistsException("Country [" + entity.getName() + "] already exists");
        }
        return this.repository.save(entity);
    }

    @Override
    public void update(Long id, Country entity) {
        Country country = findById(id);
        if (!country.equals(entity)) {
            country.setName(entity.getName());
            country.setAlpha2Code(entity.getAlpha2Code());
            country.setAlpha3Code(entity.getAlpha3Code());
            country.setNumeric(entity.getNumeric());
            this.repository.save(country);
        }
    }

    @Override
    public void deleteById(Long id) {
        Country country = findById(id);
        this.repository.delete(country);
    }
}
