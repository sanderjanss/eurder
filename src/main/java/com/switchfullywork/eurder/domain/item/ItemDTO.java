package com.switchfullywork.eurder.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ItemDTO {

    private final UUID itemId;
    private final String name;
    private final String description;
    private final double price;
    private final int amountStock;

}
