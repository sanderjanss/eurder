package com.switchfullywork.eurder.domain.itemdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateItemRequest {

    private final String name;
    private final String description;
    private final double price;
    private final int amountStock;

}
