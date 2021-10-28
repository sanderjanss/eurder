package com.switchfullywork.eurder.domain.item;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {

    private final UUID itemGroupId;
    private final UUID itemId;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(UUID itemId, int amount, LocalDate shippingDate) {
        this.itemGroupId = UUID.randomUUID();
        this.itemId = itemId;
        this.amount = amount;
        this.shippingDate = calulateShippingDate(shippingDate);
    }

    public UUID getItemGroupId() {
        return itemGroupId;
    }

    public UUID getItemId() {
        return itemId;
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

    public static class ItemGroupBuilder{

        private UUID itemid;
        private int amount;
        private LocalDate shippingDate;

        public ItemGroupBuilder setItemid(UUID itemid) {
            this.itemid = itemid;
            return this;
        }

        public ItemGroupBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ItemGroupBuilder setShippingDate(LocalDate shippingDate) {
            this.shippingDate = shippingDate;
            return this;
        }

        public ItemGroup build(){
            return new ItemGroup(this.itemid, this.amount, this.shippingDate);
        }
    }
}
