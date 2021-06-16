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
public class ItemService implements IService<Item,Long> {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Collection<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item findById(Long id) {
        return this.itemRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Item with id [" + id + "] not found"));
    }

    @Override
    public Item save(Item item) {
        Example<Item> itemExample = Example.of(item);
        if (this.itemRepository.exists(itemExample)){
            throw new EntityExistsException("Item [" + item.getName() + " " + item.getVendorCode() +
                    "] already exists");
        }
        return this.itemRepository.save(item);
    }

    @Override
    public void update(Long id, Item updatedItem) {
        Item item = findById(id);
        if (!item.equals(updatedItem)){
            item.setName(updatedItem.getName());
            item.setManufacturer(updatedItem.getManufacturer());
            item.setPrice(updatedItem.getPrice());
            item.setVendorCode(updatedItem.getVendorCode());
            this.itemRepository.save(item);
        }
    }

    @Override
    public void deleteById(Long id) {
        Item item = findById(id);
        this.itemRepository.delete(item);
    }
}
