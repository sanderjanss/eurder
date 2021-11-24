package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.orderdto.OrderResponse;
import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;

import java.util.UUID;

public interface OrderService {

    OrderResponse registerOrder(CreateOrderRequest order);

    ReportResponse getReport(int customerId);
}
