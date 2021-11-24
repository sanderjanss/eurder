package com.switchfullywork.eurder.domain.itemdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateItemGroupRequest {

    private final Integer itemId;
    private final int amount;


}
