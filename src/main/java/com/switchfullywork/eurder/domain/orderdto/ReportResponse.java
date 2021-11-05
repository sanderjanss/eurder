package com.switchfullywork.eurder.domain.orderdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ReportResponse {

    private final List<OrderResponse> listOfOrders;
    private final double totalPriceAllOrders;
}
