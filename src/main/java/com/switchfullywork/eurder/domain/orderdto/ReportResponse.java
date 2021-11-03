package com.switchfullywork.eurder.domain.orderdto;

import com.switchfullywork.eurder.domain.orderdto.OrderResponse;
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
