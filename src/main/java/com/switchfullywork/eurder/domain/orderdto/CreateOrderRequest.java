package com.switchfullywork.eurder.domain.orderdto;

import com.switchfullywork.eurder.domain.itemdto.CreateItemGroupRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderRequest {

    private final Integer customerId;
    private final List<CreateItemGroupRequest> listOfItemGroups;

}
