package com.switchfullywork.eurder.domain.orderdto;

import com.switchfullywork.eurder.domain.itemdto.CreateItemGroupRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderRequest {

    private final UUID customerId;
    private final List<CreateItemGroupRequest> listOfItemGroups;

}
