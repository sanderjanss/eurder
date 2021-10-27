package com.switchfullywork.eurder.domain.item;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {

    private final UUID id;
    private final UUID itemid;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(UUID id, UUID itemid, int amount, LocalDate shippingDate) {
        this.id = UUID.randomUUID();
        this.itemid = itemid;
        this.amount = amount;
        this.shippingDate = calulateShippingDate(shippingDate);
    }

    public UUID getId() {
        return id;
    }

    public UUID getItemid() {
        return itemid;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    private LocalDate calulateShippingDate(LocalDate shippingDate){
        return shippingDate;
    }
}
