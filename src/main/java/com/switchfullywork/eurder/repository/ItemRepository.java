package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.item.Item;

public interface ItemRepository {

    void registerItem(Item item);
    void giveAllItemsWithStockResuplyUrgency();
}
