package com.example.onlineshop.controller;

import com.example.onlineshop.model.Item;
import com.example.onlineshop.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("api/v1/items")
public class ItemController implements IRestController<Item, Long> {

    private final IService<Item, Long> service;

    @Autowired
    public ItemController(IService<Item, Long> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Collection<Item>> findAll() {
        Collection<Item> itemCollection = this.service.findAll();
        return new ResponseEntity<>(itemCollection, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> findById(Long id) {
        Item item = this.service.findById(id);
        return new ResponseEntity<>(item, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Item> save(Item item) {
        Item savedItem = this.service.save(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, Item item) {
        this.service.update(id, item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
