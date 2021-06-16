package com.example.onlineshop.service;

import java.util.Collection;

public interface IService<T, K> {

    Collection<T> findAll();

    T findById(K id);

    T save(T entity);

    void update(K id, T entity);

    void deleteById(K id);
}
