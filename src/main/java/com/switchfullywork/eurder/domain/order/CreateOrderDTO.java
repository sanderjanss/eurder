package com.switchfullywork.eurder.domain.order;

import com.switchfullywork.eurder.domain.item.CreateItemGroupDTO;
import com.switchfullywork.eurder.domain.item.ItemGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderDTO {

    private final UUID customerId;
    private final List<CreateItemGroupDTO> listOfItemGroups;

}
