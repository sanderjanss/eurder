package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.ReportDTO;
import com.switchfullywork.eurder.domain.order.CreateOrderDTO;

import java.util.UUID;

public interface OrderService {

    double registerOrder(CreateOrderDTO order);

    ReportDTO getReport(UUID customerId);
}
