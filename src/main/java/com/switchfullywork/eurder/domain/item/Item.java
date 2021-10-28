package com.switchfullywork.eurder.domain.item;

import java.util.UUID;

public class Item {

    private final UUID itemId;
    private final String name;
    private final String description;
    private final double price;
    private final int amountStock;

    public Item(String name, String description, double price, int amountStock) {
        this.itemId = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountStock = amountStock;
    }

    public UUID getItemId() {
        return itemId;
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + itemId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amountStock=" + amountStock +
                '}';
    }

    public static class ItemBuilder{

        private String name;
        private String description;
        private double price;
        private int amountStock;

        public ItemBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ItemBuilder setAmountStock(int amountStock) {
            this.amountStock = amountStock;
            return this;
        }

        public Item build(){
            return new Item(this.name, this.description, this.price, this.amountStock);
        }
    }
}
