package com.example.onlineshop.controller;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface IRestController<T, K> {

    ResponseEntity<Collection<T>> findAll();

    ResponseEntity<T> findById(K id);

    ResponseEntity<Void> deleteById(K id);

    ResponseEntity<T> save(T entity);

    ResponseEntity<Void> update(K id, T entity);
}
