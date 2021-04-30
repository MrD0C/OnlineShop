package com.example.onlineshop.exception.customer;

public class CustomerAlreadyExistException extends RuntimeException{

    public CustomerAlreadyExistException(String lastName,String firstName){
        super("Customer ["+ lastName + " " + firstName + "] already exist");
    }
}
