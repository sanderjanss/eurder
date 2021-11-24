package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.domain.itemdto.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse registerItem(CreateItemRequest item);

    void updateItem(CreateItemRequest item, int itemId);

    List<ItemResponse> getAllItems();
}
