package com.switchfullywork.eurder.domain.itemdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ItemGroupResponse {
    private final UUID itemGroupId;
    private final UUID itemId;
    private final int amount;
    private final LocalDate shippingDate;

}
