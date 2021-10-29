package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.item.CreateItemDTO;

import java.util.UUID;

public interface ItemService {
    void registerItem(CreateItemDTO item, UUID userId);

    void updateItem(CreateItemDTO item, UUID userId, UUID itemId);
}
