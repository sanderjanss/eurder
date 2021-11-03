package com.switchfullywork.eurder.domain;

import com.switchfullywork.eurder.domain.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ReportDTO {

    private final List<OrderDTO> listOfOrders;
    private final double totalPriceAllOrders;
}
