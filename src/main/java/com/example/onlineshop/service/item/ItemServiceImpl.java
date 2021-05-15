package com.example.onlineshop.service.item;

import com.example.onlineshop.model.Item;
import com.example.onlineshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item findItem(Long id) {
        return this.itemRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Item with id [" + id + "] not found"));
    }

    @Override
    public Item saveItem(Item item) {
        Example<Item> itemExample = Example.of(item);
        if (this.itemRepository.exists(itemExample)){
            throw new EntityExistsException("Item [" + item.getName() + " " + item.getVendorCode() +
                    "] already exists");
        }
        return this.itemRepository.save(item);
    }

    @Override
    public void updateItem(Long id, Item updatedItem) {
        Item item = findItem(id);
        if (!item.equals(updatedItem)){
            item.setName(updatedItem.getName());
            item.setManufacturer(updatedItem.getManufacturer());
            item.setPrice(updatedItem.getPrice());
            item.setVendorCode(updatedItem.getVendorCode());
            this.itemRepository.save(item);
        }
    }

    @Override
    public void deleteItem(Long id) {
        Item item = findItem(id);
        this.itemRepository.delete(item);
    }

    @Override
    public Collection<Item> findAllItems() {
        return this.itemRepository.findAll();
    }
}
