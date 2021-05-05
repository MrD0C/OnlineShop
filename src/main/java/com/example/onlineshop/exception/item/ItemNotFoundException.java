package com.example.onlineshop.exception.item;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(Long id){
        super("Item with id - " + id + " not found");
    }
}
