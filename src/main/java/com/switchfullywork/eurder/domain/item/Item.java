package com.switchfullywork.eurder.domain.item;

import java.util.UUID;

public class Item {

    private final UUID id;
    private final String name;
    private final String description;
    private final double price;
    private final int amountStock;

    public Item(UUID id, String name, String description, double price, int amountStock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountStock = amountStock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountStock() {
        return amountStock;
    }
}
