package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.order.CreateOrderDTO;
import com.switchfullywork.eurder.domain.order.Order;
import com.switchfullywork.eurder.domain.order.OrderDTO;

import java.util.UUID;

public interface OrderService {

    double registerOrder(CreateOrderDTO order);
    OrderDTO getOrder(UUID customerId);
}
