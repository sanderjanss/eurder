package com.switchfullywork.eurder.domain.order;

import com.switchfullywork.eurder.domain.item.ItemGroup;

import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID customerId;
    private final List<ItemGroup> listOfItemGroups;
    private final double totalPrice;

    public Order(UUID id, UUID customerId, List<ItemGroup> listOfItemGroups) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.listOfItemGroups = listOfItemGroups;
        this.totalPrice = calculateTotalPrice();
    }

    public UUID getId() {
        return id;
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

    public double calculateTotalPrice(){
        return 0;
    }
}
