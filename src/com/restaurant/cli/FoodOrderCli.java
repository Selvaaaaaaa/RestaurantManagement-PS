
package com.restaurant.cli;

import com.restaurant.model.FoodOrder;
import com.restaurant.service.FoodOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodOrderCli {

    private final Scanner scanner;
    private final FoodOrderService service;

    public FoodOrderCli(Scanner scanner) {
        this.scanner = scanner;
        this.service = new FoodOrderService();
    }

    public void start() {
        int choice = -1;

        do {
            System.out.println("\n=== FOOD ORDERING ===");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View All Orders");
            System.out.println("4. Find Order by ID");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        showMenu();
                        break;

                    case 2:
                        placeOrder();
                        break;

                    case 3:
                        showOrders();
                        break;

                    case 4:
                        findOrder();
                        break;

                    case 0:
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
    }

    private void showMenu() {
        service.getMenu().forEach(System.out::println);
    }

    private void placeOrder() {
        showMenu();

        List<Integer> itemIds = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        while (true) {
            System.out.print("Enter item ID (0 to finish): ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (id == 0) {
                break;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            itemIds.add(id);
            quantities.add(quantity);
        }

        int[] ids = itemIds.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        int[] counts = quantities.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        FoodOrder order = service.createOrder(ids, counts);

        System.out.println("Order placed successfully.");
        System.out.println(order);
    }

    private void showOrders() {
        List<FoodOrder> orders = service.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No food orders found.");
            return;
        }

        orders.forEach(System.out::println);
    }

    private void findOrder() {
        System.out.print("Enter order ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        FoodOrder order = service.getOrderById(id);

        if (order == null) {
            System.out.println("Order not found.");
        } else {
            System.out.println(order);
        }
    }
}
