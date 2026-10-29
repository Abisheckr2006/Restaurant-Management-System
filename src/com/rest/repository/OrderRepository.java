package com.rest.repository;

import com.rest.models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();
    private int nextId = 1;

    public OrderRepository() {
        orders.add(new Order(1, 1, "Burger", 2, 120.0));
        orders.add(new Order(2, 2, "Pizza", 1, 250.0));
        orders.add(new Order(3, 3, "Pasta", 2, 180.0));
        nextId = 4;
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order findById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public Order save(Order order) {
        if (findById(order.getId()) == null) {
            orders.add(order);
            return order;
        }

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == order.getId()) {
                orders.set(i, order);
                return order;
            }
        }
        return order;
    }

    public int getNextId() {
        return nextId++;
    }

    public boolean updateStatus(int id, Order.OrderStatus status) {
        Order order = findById(id);
        if (order == null) {
            return false;
        }
        order.setStatus(status);
        return true;
    }
}
