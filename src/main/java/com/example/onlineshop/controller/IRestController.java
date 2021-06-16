package com.example.onlineshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
public interface IRestController<T, K extends Number> {

    @GetMapping("/all")
    ResponseEntity<Collection<T>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<T> findById(@Min(value = 1, message = "ID must be equal or greater than 1")
                               @PathVariable K id);

    @PostMapping("/save")
    ResponseEntity<T> save(@Valid @RequestBody T entity);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@Min(value = 1, message = "ID must be equal or greater than 1")
                                @PathVariable K id, @Valid @RequestBody T entity);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@Min(value = 1, message = "ID must be equal or greater than 1")
                                    @PathVariable K id);
}
