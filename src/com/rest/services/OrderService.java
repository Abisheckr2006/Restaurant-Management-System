package com.rest.services;

import com.rest.models.Order;
import com.rest.repository.OrderRepository;
import java.util.List;

public class OrderService {
    private final OrderRepository repository;

    public OrderService() {
        repository = new OrderRepository();
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(int id) {
        return repository.findById(id);
    }

    public Order createOrder(int tableId, String itemName, int quantity, double unitPrice) {
        int id = repository.getNextId();
        Order order = new Order(id, tableId, itemName, quantity, unitPrice);
        return repository.save(order);
    }

    public boolean updateOrderStatus(int id, Order.OrderStatus status) {
        return repository.updateStatus(id, status);
    }
}
