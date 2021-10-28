package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.order.Order;

public interface OrderRepository {

    void registerOrder(Order order);
}
