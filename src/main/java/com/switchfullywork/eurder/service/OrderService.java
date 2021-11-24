package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.orderdto.OrderResponse;
import com.switchfullywork.eurder.domain.orderdto.ReportResponse;

import java.util.List;

public interface OrderService {

    OrderResponse registerOrder(CreateOrderRequest order);

    ReportResponse getReport(int customerId);

    List<OrderResponse> getAllOrders();
}
