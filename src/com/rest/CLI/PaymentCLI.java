
package com.rest.CLI;

import com.rest.models.Payment;
import com.rest.payment.CashPayment;
import com.rest.services.PaymentService;
import java.util.List;
import java.util.Scanner;

public class PaymentCLI {

    private final PaymentService service;
    private final Scanner scanner;

    public PaymentCLI(Scanner scanner, PaymentService service) {
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

    private double readAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            try {
                double amount = Double.parseDouble(input);

                if (Double.isNaN(amount)
                        || Double.isInfinite(amount)
                        || amount <= 0) {
                    System.out.println(
                        "Amount must be a positive number."
                    );
                    continue;
                }

                return amount;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n=== RESTAURANT PAYMENT MANAGEMENT ===");
            System.out.println("1. Process cash payment");
            System.out.println("2. View all payments");
            System.out.println("3. Find payment by ID");
            System.out.println("0. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> {
                    double amount = readAmount(
                        "Enter payment amount: Rs. "
                    );

                    Payment payment = service.processPayment(
                        new CashPayment(), amount
                    );

                    System.out.println(
                        "Payment recorded successfully: " + payment
                    );
                }

                case 2 -> {
                    List<Payment> payments = service.getAllPayments();

                    System.out.println("\n=== ALL PAYMENTS ===");

                    if (payments.isEmpty()) {
                        System.out.println("No payments found.");
                    } else {
                        for (Payment listedPayment : payments) {
                            System.out.println(listedPayment);
                        }
                    }
                }

                case 3 -> {
                    int paymentId = readInt("Enter payment ID: ");

                    Payment foundPayment =
                        service.getPaymentById(paymentId);

                    if (foundPayment == null) {
                        System.out.println("Payment not found.");
                    } else {
                        System.out.println(foundPayment);
                    }
                }

                case 0 -> {
                    System.out.println("Thank you!");
                    return;
                }

                default ->
                    System.out.println("Invalid choice.");
            }
        }
    }
}
