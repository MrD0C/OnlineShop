package com.example.onlineshop.service.item;

import com.example.onlineshop.model.Item;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ItemService {

    Item findItem(Long id);

    Item saveItem(Item item);

    void updateItem(Long id, Item updatedItem);

    void deleteItem(Long id);

    Collection<Item> findAllItems();
}
