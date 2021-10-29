package com.switchfullywork.eurder.domain.item;

import java.time.LocalDate;
import java.util.UUID;

public class CreateItemGroupDTO {

    private final UUID itemId;
    private final int amount;
    private final LocalDate shippingDate;

    public CreateItemGroupDTO(UUID itemId, int amount, LocalDate shippingDate) {
        this.itemId = itemId;
        this.amount = amount;
        this.shippingDate = shippingDate;
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

    public static class CreateItemGroupDTOBuilder {

        private UUID itemId;
        private int amount;
        private LocalDate shippingDate;

        public CreateItemGroupDTOBuilder setItemId(UUID itemId) {
            this.itemId = itemId;
            return this;
        }

        public CreateItemGroupDTOBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public CreateItemGroupDTOBuilder setShippingDate(LocalDate shippingDate) {
            this.shippingDate = shippingDate;
            return this;
        }

        public CreateItemGroupDTO build() {
            return new CreateItemGroupDTO(this.itemId, this.amount, this.shippingDate);
        }
    }
}
