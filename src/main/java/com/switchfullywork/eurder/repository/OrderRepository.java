package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    void registerOrder(Order order);
    List<Order> getOrders(UUID customerId);
}
