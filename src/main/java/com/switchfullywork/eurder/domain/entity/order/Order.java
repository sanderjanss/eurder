package com.switchfullywork.eurder.domain.entity.order;

import com.switchfullywork.eurder.domain.entity.item.ItemGroup;

import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID orderId;
    private final UUID customerId;
    private final List<ItemGroup> listOfItemGroups;
    private final double totalPrice;

    public Order(UUID customerId, List<ItemGroup> listOfItemGroups, double totalPrice) {
        this.orderId = UUID.randomUUID();
        this.customerId = customerId;
        this.listOfItemGroups = listOfItemGroups;
        this.totalPrice = totalPrice;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getListOfItemGroups() {
        return listOfItemGroups;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


    public static class OrderBuilder {

        private UUID customerId;
        private List<ItemGroup> listOfItemGroups;
        private double totalPrice;


        public OrderBuilder setCustomerId(UUID customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderBuilder setListOfItemGroups(List<ItemGroup> listOfItemGroups) {
            this.listOfItemGroups = listOfItemGroups;
            return this;
        }

        public OrderBuilder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order build() {
            return new Order(this.customerId, this.listOfItemGroups, this.totalPrice);
        }
    }
}
