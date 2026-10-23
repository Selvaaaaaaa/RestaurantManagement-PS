package com.restaurant.cli;

import com.restaurant.model.Table;
import com.restaurant.service.TableService;
import java.util.Scanner;

public class TableCLI {

    private final TableService service;
    private final Scanner scanner = new Scanner(System.in);

    public TableCLI(TableService service) {
        this.service = service;
    }

    public void start() {
        int choice = -1;

        do {
            System.out.println("\n=== TABLE MANAGEMENT ===");
            System.out.println("1. View All Tables");
            System.out.println("2. Find Table by ID");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        for (Table table : service.getAllTables()) {
                            System.out.println(table);
                        }
                        break;

                    case 2:
                        System.out.print("Enter table ID: ");
                        int id = scanner.nextInt();
                        Table table = service.getTableById(id);
                        System.out.println(table);
                        break;

                    case 0:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }

        } while (choice != 0);

        scanner.close();
    }
}
