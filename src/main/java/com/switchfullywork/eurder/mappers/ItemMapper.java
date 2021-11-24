package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.entity.item.Item;
import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.domain.itemdto.ItemResponse;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toEntity(CreateItemRequest createItemRequest) {
        return new Item.Builder()
                .withName(createItemRequest.getName())
                .withDescription(createItemRequest.getDescription())
                .withPrice(createItemRequest.getPrice())
                .withAmountStock(createItemRequest.getAmountStock())
                .build();
    }

    public ItemResponse toDto(Item item){
        return ItemResponse.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .amountStock(item.getAmountStock())
                .build();
    }
}
