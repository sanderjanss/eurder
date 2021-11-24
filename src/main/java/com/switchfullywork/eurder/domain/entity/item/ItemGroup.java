package com.switchfullywork.eurder.domain.entity.item;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "item_group")
public class ItemGroup {

    @Id
    @SequenceGenerator(name = "item_group_item_group_id_seq", sequenceName = "item_group_item_group_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_group_item_group_id_seq")
    private int itemGroupId;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "amount")
    private int amount;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;


    public ItemGroup(Item item, int amount, LocalDate shippingDate) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = shippingDate;
    }

    public ItemGroup() {
    }

    public int getItemGroupId() {
        return itemGroupId;
    }

    public Item getItem() {
        return item;
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


    public static final class Builder {
        private Item item;
        private int amount;
        private LocalDate shippingDate;

        public Builder withItem(Item item) {
            this.item = item;
            return this;
        }

        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder withShippingDate(LocalDate shippingDate) {
            this.shippingDate = shippingDate;
            return this;
        }

        public ItemGroup build() {
            return new ItemGroup(item, amount, shippingDate);
        }
    }
}
