package com.example.onlineshop.controller.open;

import com.example.onlineshop.model.Item;
import com.example.onlineshop.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Validated
@RestController
@RequestMapping("api/v1/items")
public class ItemRestController implements IRestController<Item, Long> {

    private final IService<Item, Long> service;

    @Autowired
    public ItemRestController(IService<Item, Long> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Collection<Item>> findAll() {
        Collection<Item> collection = this.service.findAll();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> findById(Long id) {
        Item entity = this.service.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> save(Item entity) {
        Item savedEntity = this.service.save(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, Item entity) {
        this.service.update(id, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
