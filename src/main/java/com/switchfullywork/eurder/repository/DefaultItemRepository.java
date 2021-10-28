package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultItemRepository implements ItemRepository{

    private final Map<UUID, Item> itemByIdDatabase;

    public DefaultItemRepository() {
        this.itemByIdDatabase = new ConcurrentHashMap<>();
    }


    @Override
    public void registerItem(Item item) {
        itemByIdDatabase.put(item.getItemId(), item);
    }

    @Override
    public boolean contains(UUID itemId) {
        return itemByIdDatabase.containsValue(itemId);
    }

    @Override
    public Item getById(UUID itemId) {
        return itemByIdDatabase.get(itemId);
    }

    @Override
    public void giveAllItemsWithStockResuplyUrgency() {

    }
}
