package com.switchfullywork.eurder.domain.item;

public class CreateItemDTO {

    private final String name;
    private final String description;
    private final double price;
    private final int amountStock;

    public CreateItemDTO(String name, String description, double price, int amountStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountStock = amountStock;
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
        return "CreateItemDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amountStock=" + amountStock +
                '}';
    }

    public static class CreateItemDTOBuilder {

        private String name;
        private String description;
        private double price;
        private int amountStock;

        public CreateItemDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CreateItemDTOBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CreateItemDTOBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public CreateItemDTOBuilder setAmountStock(int amountStock) {
            this.amountStock = amountStock;
            return this;
        }

        public CreateItemDTO build() {
            return new CreateItemDTO(this.name, this.description, this.price, this.amountStock);
        }
    }
}
