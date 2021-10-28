package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.item.CreateItemDTO;
import com.switchfullywork.eurder.domain.item.Item;

import java.util.UUID;

public interface ItemService {
    void registerItem(CreateItemDTO item, UUID userId);
}
