
package com.restaurant.service;

import com.restaurant.model.FoodItem;
import com.restaurant.model.FoodOrder;
import com.restaurant.model.OrderLine;
import com.restaurant.repository.FoodOrderRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodOrderService {

    private final List<FoodItem> menu = Arrays.asList(
            new FoodItem(1, "Classic Burger", 199.00),
            new FoodItem(2, "Margherita Pizza", 299.00),
            new FoodItem(3, "Pasta Alfredo", 249.00),
            new FoodItem(4, "French Fries", 99.00),
            new FoodItem(5, "Soft Drink", 49.00)
    );

    private final FoodOrderRepository repository =
            new FoodOrderRepository();

    private int nextOrderId = 1;

    public List<FoodItem> getMenu() {
        return new ArrayList<>(menu);
    }

    public FoodOrder createOrder(int[] itemIds, int[] quantities) {

        if (itemIds == null
                || quantities == null
                || itemIds.length == 0
                || itemIds.length != quantities.length) {

            throw new IllegalArgumentException(
                    "Select at least one menu item."
            );
        }

        List<OrderLine> lines = new ArrayList<>();

        for (int i = 0; i < itemIds.length; i++) {

            if (quantities[i] <= 0) {
                throw new IllegalArgumentException(
                        "Quantity must be greater than zero."
                );
            }

            FoodItem item = findMenuItem(itemIds[i]);

            if (item == null) {
                throw new IllegalArgumentException(
                        "Menu item not found: " + itemIds[i]
                );
            }

            lines.add(new OrderLine(item, quantities[i]));
        }

        FoodOrder order = new FoodOrder(nextOrderId++, lines);

        repository.save(order);

        return order;
    }

    private FoodItem findMenuItem(int id) {

        for (FoodItem item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public List<FoodOrder> getAllOrders() {
        return repository.findAll();
    }

    public FoodOrder getOrderById(int id) {
        return repository.findById(id);
    }
}
