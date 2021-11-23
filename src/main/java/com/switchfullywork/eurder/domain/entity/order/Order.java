package com.switchfullywork.eurder.domain.entity.order;

import com.switchfullywork.eurder.domain.entity.item.ItemGroup;
import com.switchfullywork.eurder.domain.entity.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order", schema = "eurder")
public class Order {

    @Id
    @SequenceGenerator(name = "order_order_id_seq", sequenceName = "order_order_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_order_id_seq")
    @Column(name = "order_id")
    private int orderId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", nullable = false)
    private List<ItemGroup> listOfItemGroups;
    private double totalPrice;

    public Order(User user, List<ItemGroup> listOfItemGroups, double totalPrice) {
        this.user = user;
        this.listOfItemGroups = listOfItemGroups;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<ItemGroup> getListOfItemGroups() {
        return listOfItemGroups;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addItemGroup(ItemGroup itemGroup){
        listOfItemGroups.add(itemGroup);
    }

    public static final class Builder {
        private User user;
        private List<ItemGroup> listOfItemGroups;
        private double totalPrice;


        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withListOfItemGroups(List<ItemGroup> listOfItemGroups) {
            this.listOfItemGroups = listOfItemGroups;
            return this;
        }

        public Builder withTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order build() {
            return new Order(user, listOfItemGroups, totalPrice);
        }
    }
}
