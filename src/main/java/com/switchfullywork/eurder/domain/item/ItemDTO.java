package com.switchfullywork.eurder.domain.item;

import java.util.Objects;
import java.util.UUID;

public class ItemDTO {

    private final UUID itemId;
    private final String name;
    private final String description;
    private final double price;
    private final int amountStock;

    public ItemDTO(UUID itemId, String name, String description, double price, int amountStock) {
        this.itemId = itemId;
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


    public static class ItemDTOBuilder {

        private UUID itemId;
        private String name;
        private String description;
        private double price;
        private int amountStock;

        public ItemDTOBuilder setItemId(UUID itemId) {
            this.itemId = itemId;
            return this;
        }

        public ItemDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ItemDTOBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemDTOBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ItemDTOBuilder setAmountStock(int amountStock) {
            this.amountStock = amountStock;
            return this;
        }

        public ItemDTO build() {
            return new ItemDTO(this.itemId, this.name, this.description, this.price, this.amountStock);
        }
    }
}
