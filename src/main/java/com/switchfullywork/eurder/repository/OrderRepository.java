package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.order.Order;

import java.util.UUID;

public interface OrderRepository {

    void registerOrder(Order order);
    Order getOrder(UUID customerId);
}
