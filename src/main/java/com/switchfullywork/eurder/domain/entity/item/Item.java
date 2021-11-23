package com.switchfullywork.eurder.domain.entity.item;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @SequenceGenerator(name = "item_item_id_seq", sequenceName = "item_item_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_item_id_seq")
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "amount_stock")
    private int amountStock;

    public Item(String name, String description, double price, int amountStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountStock = amountStock;
    }

    public Item() {
    }

    public int getItemId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void updateItem(Item item){
        this.name = item.name;
        this.description = item.description;
        this.price = item.price;
        this.amountStock = item.amountStock;
    }

    public static class ItemBuilder {

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

        public Item build() {
            return new Item(this.name, this.description, this.price, this.amountStock);
        }
    }
}
