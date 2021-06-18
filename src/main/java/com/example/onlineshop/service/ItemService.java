package com.example.onlineshop.service;

import com.example.onlineshop.model.Item;
import com.example.onlineshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;

@Service
public class ItemService implements IService<Item, Long> {

    private final ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Item> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Item findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item with id [" + id + "] not found"));
    }

    @Override
    public Item save(Item entity) {
        Example<Item> example = Example.of(entity);
        if (this.repository.exists(example)) {
            throw new EntityExistsException("Item [" + entity.getName() + " " + entity.getVendorCode() +
                    "] already exists");
        }
        return this.repository.save(entity);
    }

    @Override
    public void update(Long id, Item entity) {
        Item item = findById(id);
        if (!item.equals(entity)) {
            item.setName(entity.getName());
            item.setManufacturer(entity.getManufacturer());
            item.setPrice(entity.getPrice());
            item.setVendorCode(entity.getVendorCode());
            this.repository.save(item);
        }
    }

    @Override
    public void deleteById(Long id) {
        Item item = findById(id);
        this.repository.delete(item);
    }
}
