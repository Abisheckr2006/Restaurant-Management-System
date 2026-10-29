package com.rest.CLI;
import com.rest.models.Table;
import com.rest.services.TableService;
import java.util.List;
import java.util.Scanner;

public class TableCLI {
    private final TableService service;
    private final Scanner scanner;

    public TableCLI(Scanner scanner, TableService service) {
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

    public void start() {
        System.out.println("=== RESTAURANT TABLE MANAGEMENT ===");
        System.out.println("1. View all tables");
        System.out.println("2. Occupy a table");
        System.out.println("3. Free a table");
        System.out.println("0. Exit");

        int choice = readInt("Enter your choice: ");

        if (choice == 1) {
            List<Table> tables = service.getAllTables();
            System.out.println("\n=== ALL TABLES ===");
            for (Table table : tables) {
                System.out.println(table);
            }
        } else if (choice == 2) {
            int tableId = readInt("Enter table id: ");
            boolean updated = service.occupyTable(tableId);
            if (updated) {
                System.out.println("Table " + tableId + " is now occupied.");
            } else {
                System.out.println("Table not found.");
            }
        } else if (choice == 3) {
            int tableId = readInt("Enter table id: ");
            boolean updated = service.freeTable(tableId);
            if (updated) {
                System.out.println("Table " + tableId + " is now free.");
            } else {
                System.out.println("Table not found.");
            }
        } else if (choice == 0) {
            System.out.println("Thank you");
        } else {
            System.out.println("Invalid choice.");
        }
    }

}
