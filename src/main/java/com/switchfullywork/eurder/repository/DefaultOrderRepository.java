package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultOrderRepository implements OrderRepository {

    private final Map<UUID, Order> orderByIdDatabase;

    public DefaultOrderRepository() {
        this.orderByIdDatabase = new ConcurrentHashMap<>();
    }

    @Override
    public void registerOrder(Order order) {
        orderByIdDatabase.put(order.getOrderId(), order);

    }

    @Override
    public Order getOrder(UUID customerId) {
        return orderByIdDatabase.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .findAny().get();
    }
}
