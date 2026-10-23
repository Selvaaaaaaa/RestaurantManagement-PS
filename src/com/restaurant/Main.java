package com.restaurant;

import com.restaurant.cli.PaymentCli;
import com.restaurant.cli.TableCLI;
import com.restaurant.cli.FoodOrderCli;
import com.restaurant.service.TableService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== RESTAURANT MANAGEMENT SYSTEM ===");
        System.out.println("1. Table Management");
        System.out.println("2. Payment Management");
        System.out.println("3. Food Ordering");
        System.out.print("Choose an option: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            TableService tableService = new TableService();
            TableCLI tableCli = new TableCLI(tableService);
            tableCli.start();
        } else if (choice == 2) {
            PaymentCli paymentCli = new PaymentCli(scanner);
            paymentCli.start();
        } else if (choice == 3) {
            scanner.nextLine();
            new FoodOrderCli(scanner).start();
        } else {
            System.out.println("Invalid option. Exiting.");
        }

        scanner.close();
    }
}
