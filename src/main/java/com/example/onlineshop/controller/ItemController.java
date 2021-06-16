package com.example.onlineshop.controller;

import com.example.onlineshop.model.Item;
import com.example.onlineshop.service.item.ItemService;
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

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Item>> findAll() {
        Collection<Item> itemCollection = this.itemService.findAllItems();
        return new ResponseEntity<>(itemCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@Min(value = 1, message = "ID must be equal or greater than 1")
                                         @PathVariable Long id) {
        Item item = this.itemService.findItem(id);
        return new ResponseEntity<>(item, HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Item> save(@Valid @RequestBody Item item) {
        Item savedItem = this.itemService.saveItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Min(value = 1, message = "ID must be equal or greater than 1")
                                           @PathVariable Long id) {
        this.itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Min(value = 1, message = "ID must be equal or greater than 1")
                                       @PathVariable Long id,
                                       @Valid @RequestBody Item item) {
        this.itemService.updateItem(id, item);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
