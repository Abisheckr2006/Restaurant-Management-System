package com.rest;


import com.rest.CLI.OrderCLI;
import com.rest.CLI.PaymentCLI;
import com.rest.CLI.TableCLI;
import com.rest.services.OrderService;
import com.rest.services.PaymentService;
import com.rest.services.TableService;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("=== RESTAURANT MANAGEMENT ===");
            System.out.println("1. Table Menu");
            System.out.println("2. Order Menu");
            System.out.println("3. Payment Menu");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    TableService tableService = new TableService();
                    TableCLI tableCLI = new TableCLI(scanner, tableService);
                    tableCLI.start();
                    keepRunning = false;
                    break;
                case 2:
                    OrderService orderService = new OrderService();
                    OrderCLI orderCLI = new OrderCLI(scanner, orderService);
                    orderCLI.start();
                    keepRunning = false;
                    break;
                case 3:
                    PaymentService paymentService = new PaymentService();
                    PaymentCLI paymentCLI = new PaymentCLI(scanner, paymentService);
                    paymentCLI.start();
                    keepRunning = false;
                    break;
                case 0:
                    System.out.println("Thank you");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
