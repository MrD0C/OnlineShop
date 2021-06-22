package com.example.onlineshop.controller.open.order;

import com.example.onlineshop.model.order.OrderDTO;
import com.example.onlineshop.model.order.OrderInfo;
import com.example.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<OrderInfo>> findAll(@PathVariable Long id) {
        List<OrderInfo> list = this.orderService.findAllOrders(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        this.orderService.createOrder(orderDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
