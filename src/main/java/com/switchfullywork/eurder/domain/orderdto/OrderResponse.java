package com.switchfullywork.eurder.domain.orderdto;

import com.switchfullywork.eurder.domain.entity.item.ItemGroup;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.itemdto.ItemGroupResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderResponse {

    private final int orderId;
    private final List<ItemGroupResponse> listOfItemGroups;
    private final double totalPrice;

}
