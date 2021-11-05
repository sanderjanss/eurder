package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.item.Item;
import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.ItemAllreadyExistsException;
import com.switchfullywork.eurder.mappers.ItemMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultItemService implements ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;
    private final ItemMapper itemMapper;

    @Autowired
    public DefaultItemService(ItemRepository itemRepository, UserService userService, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.itemMapper = itemMapper;
    }

    public void registerItem(CreateItemRequest createItemRequest, UUID adminId) {
        assertValidItem(createItemRequest);
        assertItemNotPartOfDatabase(createItemRequest);
        userService.assertValidUser(adminId);
        userService.assertAuthorizedUser(adminId);

        Item item = itemMapper.toItem(createItemRequest);
        itemRepository.registerItem(item);
    }

    @Override
    public void updateItem(CreateItemRequest createItemRequest, UUID adminId, UUID itemId) {
        assertValidItem(createItemRequest);
        assertItemAllreadyPartOfDatabase(itemId);
        userService.assertValidUser(adminId);
        userService.assertAuthorizedUser(adminId);

        Item updatedItem = itemMapper.toUpdatedItem(createItemRequest, itemId);
        itemRepository.updateItem(updatedItem);
    }


    public void assertValidItem(CreateItemRequest createItemRequest) {
        if (createItemRequest == null) {
            throw new InvalidItemException("Not a valid item.");
        }
    }

    public void assertItemNotPartOfDatabase(CreateItemRequest createItemRequest) {
        Item item = itemMapper.toItem(createItemRequest);
        if (itemRepository.contains(item)) {
            throw new ItemAllreadyExistsException("This item is allready registered.");
        }
    }

    public void assertItemAllreadyPartOfDatabase(UUID itemId) {
        if (!itemRepository.contains(itemId)) {
            throw new InvalidItemException("This item is not part of the database.");
        }
    }

}
