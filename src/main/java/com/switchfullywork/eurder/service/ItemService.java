package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;

import java.util.UUID;

public interface ItemService {
    void registerItem(CreateItemRequest item);

    void updateItem(CreateItemRequest item, int itemId);
}
