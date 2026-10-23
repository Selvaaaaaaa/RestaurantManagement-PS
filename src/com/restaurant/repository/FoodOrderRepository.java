
package com.restaurant.repository;

import com.restaurant.model.FoodOrder;

import java.util.ArrayList;
import java.util.List;

public class FoodOrderRepository {

    private final List<FoodOrder> orders = new ArrayList<>();

    public void save(FoodOrder order) {
        orders.add(order);
    }

    public List<FoodOrder> findAll() {
        return new ArrayList<>(orders);
    }

    public FoodOrder findById(int id) {
        for (FoodOrder order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }
}
