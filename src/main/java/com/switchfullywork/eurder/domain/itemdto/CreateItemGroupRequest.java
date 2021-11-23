package com.switchfullywork.eurder.domain.itemdto;

import com.switchfullywork.eurder.domain.entity.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CreateItemGroupRequest {

    private final Integer itemId;
    private final int amount;


}
