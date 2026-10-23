package com.restaurant.model;

public class OrderLine {
    private final FoodItem item;
    private final int quantity;

    public OrderLine(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public FoodItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return item.getPrice() * quantity; }

    @Override
    public String toString() {
        return item.getName() + " x " + quantity + " = Rs. " + String.format("%.2f", getSubtotal());
    }
}
