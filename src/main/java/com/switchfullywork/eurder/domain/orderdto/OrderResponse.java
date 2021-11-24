package com.switchfullywork.eurder.domain.orderdto;

import com.switchfullywork.eurder.domain.itemdto.ItemGroupResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderResponse {

    private final int orderId;
    private final List<ItemGroupResponse> listOfItemGroups;
    private final double totalPrice;

}
