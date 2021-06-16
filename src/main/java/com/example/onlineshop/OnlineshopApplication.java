package com.example.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineshopApplication {

    //Todo добавить логгер (долгий ящик)
    //Todo добавить класс Order
    //Todo реализовать базовые Api взаимодействия для класса Transaction
    //Todo реализовать базовые Api взаимодействия для класса Country
    //Todo добавить тестовые сущности Country в SQL скрипт
    public static void main(String[] args) {
        SpringApplication.run(OnlineshopApplication.class, args);
    }

}
