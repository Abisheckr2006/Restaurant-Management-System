package com.rest.CLI;

import com.rest.models.Order;
import com.rest.services.OrderService;
import java.util.List;
import java.util.Scanner;

public class OrderCLI {
    private final OrderService service;
    private final Scanner scanner;

    public OrderCLI(Scanner scanner, OrderService service) {
        this.scanner = scanner;
        this.service = service;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public void start() {
        System.out.println("=== RESTAURANT ORDER MANAGEMENT ===");
        System.out.println("1. View all orders");
        System.out.println("2. Add order");
        System.out.println("3. Update order status");
        System.out.println("0. Exit");

        int choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1 -> {
                List<Order> orders = service.getAllOrders();
                System.out.println("\n=== ALL ORDERS ===");
                for (Order order : orders) {
                    System.out.println(order);
                }
            }
            case 2 -> {
                int tableId = readInt("Enter table id: ");
                System.out.print("Enter item name: ");
                String itemName = scanner.nextLine();
                int quantity = readInt("Enter quantity: ");
                double unitPrice = readDouble("Enter unit price: ");

                Order createdOrder = service.createOrder(tableId, itemName, quantity, unitPrice);
                System.out.println("Order added successfully: " + createdOrder);
            }
            case 3 -> {
                int orderId = readInt("Enter order id: ");
                System.out.println("Status options: PENDING, PREPARING, READY, SERVED, PAID");
                while (true) {
                    System.out.print("Enter new status: ");
                    String inputStatus = scanner.nextLine().trim().toUpperCase();
                    try {
                        Order.OrderStatus status = Order.OrderStatus.valueOf(inputStatus);
                        boolean updated = service.updateOrderStatus(orderId, status);
                        if (updated) {
                            System.out.println("Order status updated successfully.");
                        } else {
                            System.out.println("Order not found.");
                        }
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status. Please enter one of: PENDING, PREPARING, READY, SERVED, PAID");
                    }
                }
            }
            case 0 -> System.out.println("Thank you");
            default -> System.out.println("Invalid choice.");
        }
    }
}
