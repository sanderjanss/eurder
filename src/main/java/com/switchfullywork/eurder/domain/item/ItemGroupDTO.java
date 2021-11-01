package com.switchfullywork.eurder.domain.item;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroupDTO {
    private final UUID itemGroupId;
    private final UUID itemId;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroupDTO(UUID itemGroupId, UUID itemId, int amount, LocalDate shippingDate) {
        this.itemGroupId = itemGroupId;
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

    private LocalDate calulateShippingDate(LocalDate shippingDate) {
        return shippingDate;
    }

    public static class ItemGroupDTOBuilder {

        private UUID itemGroupId;
        private UUID itemid;
        private int amount;
        private LocalDate shippingDate;

        public ItemGroupDTOBuilder setItemGroupId(UUID itemGroupId) {
            this.itemGroupId = itemGroupId;
            return this;
        }

        public ItemGroupDTOBuilder setItemid(UUID itemid) {
            this.itemid = itemid;
            return this;
        }

        public ItemGroupDTOBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ItemGroupDTOBuilder setShippingDate(LocalDate shippingDate) {
            this.shippingDate = shippingDate;
            return this;
        }

        public ItemGroupDTO build() {
            return new ItemGroupDTO(this.itemGroupId, this.itemid, this.amount, this.shippingDate);
        }
    }
}
