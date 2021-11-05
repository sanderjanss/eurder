package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.entity.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultItemRepository implements ItemRepository {

    private final Map<UUID, Item> itemByIdDatabase;
    public final Logger logger = LoggerFactory.getLogger(DefaultItemRepository.class);


    public DefaultItemRepository() {
        this.itemByIdDatabase = new ConcurrentHashMap<>();
    }


    @Override
    public void registerItem(Item item) {
        logger.info("Registering an item...");
        itemByIdDatabase.put(item.getItemId(), item);
        logger.info("Registed Item ID: " + item.getItemId());
    }

    @Override
    public void updateItem(Item item) {
        itemByIdDatabase.put(item.getItemId(), item);
    }

    @Override
    public boolean contains(UUID itemId) {
        return itemByIdDatabase.containsKey(itemId);
    }

    @Override
    public boolean contains(Item item) {
        return itemByIdDatabase.containsValue(item);
    }

    @Override
    public Item getById(UUID itemId) {
        return itemByIdDatabase.get(itemId);
    }

    @Override
    public void giveAllItemsWithStockResuplyUrgency() {

    }
}
