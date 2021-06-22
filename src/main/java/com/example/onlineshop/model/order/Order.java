package com.example.onlineshop.model.order;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Item;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id",nullable = false)
    private final UUID order_id = UUID.randomUUID();

    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items;
}
