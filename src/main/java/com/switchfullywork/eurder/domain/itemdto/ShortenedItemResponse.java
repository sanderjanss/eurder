package com.switchfullywork.eurder.domain.itemdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ShortenedItemResponse {
    private final String name;
    private final double price;

}
