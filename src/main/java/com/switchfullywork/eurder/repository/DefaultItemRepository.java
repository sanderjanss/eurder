package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultItemRepository implements ItemRepository{

    private final Map<UUID, Item> itemByIdDatabase;

    @Autowired
    public DefaultItemRepository() {
        this.itemByIdDatabase = new ConcurrentHashMap<>();
    }


    @Override
    public void registerItem(Item item) {
        itemByIdDatabase.put(item.getId(), item);
    }

    @Override
    public void giveAllItemsWithStockResuplyUrgency() {

    }
}
