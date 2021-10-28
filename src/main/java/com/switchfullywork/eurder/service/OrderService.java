package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.order.CreateOrderDTO;

public interface OrderService {

    void registerOrder(CreateOrderDTO order);
}
