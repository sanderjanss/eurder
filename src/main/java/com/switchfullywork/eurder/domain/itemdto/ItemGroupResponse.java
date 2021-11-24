package com.switchfullywork.eurder.domain.itemdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemGroupResponse {

    private final ShortenedItemResponse shortenedItemResponse;
    private final int amount;

}
