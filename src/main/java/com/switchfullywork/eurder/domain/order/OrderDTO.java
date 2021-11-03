package com.switchfullywork.eurder.domain.order;

import com.switchfullywork.eurder.domain.item.ItemGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderDTO {

    private final UUID orderId;
    private final UUID customerId;
    private final List<ItemGroup> listOfItemGroups;
    private final double totalPrice;

}
