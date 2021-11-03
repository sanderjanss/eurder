package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.domain.entity.item.Item;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ItemMapper {

    public Item toItem(CreateItemRequest createItemRequest) {
        var item = new Item.ItemBuilder();
        return item
                .setName(createItemRequest.getName())
                .setDescription(createItemRequest.getDescription())
                .setPrice(createItemRequest.getPrice())
                .setAmountStock(createItemRequest.getAmountStock())
                .build();
    }

    public Item toUpdatedItem(CreateItemRequest createItemRequest, UUID itemId) {
        return new Item(itemId, createItemRequest.getName(), createItemRequest.getDescription(), createItemRequest.getPrice(), createItemRequest.getAmountStock());

    }
}
