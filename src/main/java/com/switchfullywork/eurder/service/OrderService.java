package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;

import java.util.UUID;

public interface OrderService {

    double registerOrder(CreateOrderRequest order);

    ReportResponse getReport(UUID customerId);
}
