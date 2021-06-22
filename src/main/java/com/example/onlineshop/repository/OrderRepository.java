package com.example.onlineshop.repository;

import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByCustomer(Customer customer);
}
