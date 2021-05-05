package com.example.onlineshop.exception.item;

public class ItemAlreadyExistException extends RuntimeException{

    public ItemAlreadyExistException(String name,String vendorCode){
        super("Item ["+ name + " " + vendorCode + "] already exist");
    }
}
