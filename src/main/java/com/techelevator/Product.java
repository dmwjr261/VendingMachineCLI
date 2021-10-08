package com.techelevator;

import java.util.Map;

public class Product {
    private final String location;
    private final String name;
    private final double price;
    private final String type;
    private int quantity;

    public Product(String location, String name, double price, String type) {
        this.location = location;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }

    @Override
    public String toString() {
        String output = location + " " + name + " " + price + " " + quantity + " Remaining";
        return output;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

}
