
package com.restaurant.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodOrder {

    private final int id;
    private final List<OrderLine> lines;
    private final double total;

    public FoodOrder(int id, List<OrderLine> lines) {
        this.id = id;
        this.lines = Collections.unmodifiableList(
                new ArrayList<>(lines)
        );

        this.total = lines.stream()
                .mapToDouble(OrderLine::getSubtotal)
                .sum();
    }

    public int getId() {
        return id;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Order ID: ")
                .append(id)
                .append("\n");

        for (OrderLine line : lines) {
            result.append("  ")
                    .append(line)
                    .append("\n");
        }

        return result.append("Total: Rs. ")
                .append(String.format("%.2f", total))
                .toString();
    }
}
