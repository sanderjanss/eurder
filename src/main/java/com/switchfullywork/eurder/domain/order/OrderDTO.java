package com.switchfullywork.eurder.domain.order;

import com.switchfullywork.eurder.domain.item.ItemGroup;

import java.util.List;
import java.util.UUID;

public class OrderDTO {

    private final UUID orderId;
    private final UUID customerId;
    private final List<ItemGroup> listOfItemGroups;
    private final double totalPrice;

    public OrderDTO(UUID orderId, UUID customerId, List<ItemGroup> listOfItemGroups, double totalPrice) {
        this.orderId = orderId;
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

    public static class OrderDTOBuilder{

        private UUID orderId;
        private UUID customerId;
        private List<ItemGroup> listOfItemGroups;
        private double totalPrice;

        public OrderDTOBuilder setOrderId(UUID orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderDTOBuilder setCustomerId(UUID customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderDTOBuilder setListOfItemGroups(List<ItemGroup> listOfItemGroups) {
            this.listOfItemGroups = listOfItemGroups;
            return this;
        }

        public OrderDTOBuilder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public OrderDTO build(){
            return new OrderDTO(this.orderId, this.customerId, this.listOfItemGroups, this.totalPrice);
        }
    }
}
