package com.example.onlineshop.service;

import com.example.onlineshop.model.order.OrderDTO;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Item;
import com.example.onlineshop.model.order.Order;
import com.example.onlineshop.model.order.OrderInfo;
import com.example.onlineshop.model.transaction.TransactionType;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.service.transaction.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ItemService itemService;
    private final TransactionService transactionService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, ItemService itemService, TransactionService transactionService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.itemService = itemService;
        this.transactionService = transactionService;
    }

    public List<OrderInfo> findAllOrders(Long customerId) {
        Customer customer = this.customerService.findById(customerId);
        List<Order> list = this.orderRepository.findAllByCustomer(customer);
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (Order order : list) {
            orderInfoList.add(mapToOrderInfo(order));
        }
        return orderInfoList;
    }

    private OrderInfo mapToOrderInfo(Order order) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUuid(order.getOrder_id());
        orderInfo.setDate(order.getDate());
        orderInfo.setCustomerFirstName(order.getCustomer().getFirstName());
        orderInfo.setCustomerLastName(order.getCustomer().getLastName());
        orderInfo.setItems(order.getItems());
        return orderInfo;
    }

    public void createOrder(OrderDTO orderDTO) {
        List<Item> list = new ArrayList<>();
        List<Long> itemIds = orderDTO.getItemIdList();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Long id : itemIds) {
            Item item = this.itemService.findById(id);
            totalPrice = totalPrice.add(item.getPrice());
            list.add(item);
        }
        Customer customer = this.customerService.findById(orderDTO.getCustomerId());
        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(list);
        this.orderRepository.save(order);
        this.transactionService.doTransaction(orderDTO.getCustomerId(), totalPrice, TransactionType.ONLINE);
    }
}
