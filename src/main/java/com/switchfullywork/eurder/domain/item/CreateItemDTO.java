package com.switchfullywork.eurder.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateItemDTO {

    private final String name;
    private final String description;
    private final double price;
    private final int amountStock;

}
