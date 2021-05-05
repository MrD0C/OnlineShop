package com.example.onlineshop.service;

import com.example.onlineshop.exception.customer.CustomerNotFoundException;
import com.example.onlineshop.exception.item.ItemAlreadyExistException;
import com.example.onlineshop.exception.item.ItemNotFoundException;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Item;
import com.example.onlineshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Item findItem(Long id) {
        return this.itemRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException(id));
    }

    @Override
    @Transactional
    public Item saveItem(Item item) {
        Example<Item> itemExample = Example.of(item);
        if (this.itemRepository.exists(itemExample)){
            throw new ItemAlreadyExistException(item.getName(),item.getVendorCode());
        }
        return this.itemRepository.save(item);
    }

    //TODO сделать рефакторинг кода
    @Override
    @Transactional
    public void updateItem(Long id, Item updatedItem) {
        Item item = findItem(id);
        if (!item.equals(updatedItem)){
            item.setName(updatedItem.getName());
            item.setManufacturer(updatedItem.getManufacturer());
            item.setPrice(updatedItem.getPrice());
            item.setVendorCode(updatedItem.getVendorCode());
        }
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        Item item = this.itemRepository.findById(id)
                .orElseThrow(()->new ItemNotFoundException(id));
        this.itemRepository.delete(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Item> findAllItems() {
        return this.itemRepository.findAll();
    }
}
