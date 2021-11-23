package com.switchfullywork.eurder.repository;

import com.switchfullywork.eurder.domain.entity.item.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    Item findItemByName(String name);

    Item findItemByItemId(int itemId);
}
