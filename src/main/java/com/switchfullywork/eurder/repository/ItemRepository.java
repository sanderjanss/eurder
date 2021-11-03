package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.entity.item.Item;

import java.util.UUID;

public interface ItemRepository {

    void registerItem(Item item);

    void updateItem(Item item);

    boolean contains(UUID itemId);

    boolean contains(Item item);

    Item getById(UUID itemId);

    void giveAllItemsWithStockResuplyUrgency();
}
