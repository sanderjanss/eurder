package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.domain.entity.item.Item;
import com.switchfullywork.eurder.domain.entity.user.Role;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.ItemAllreadyExistsException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.mappers.ItemMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultItemService implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public DefaultItemService(ItemRepository itemRepository, UserRepository userRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.itemMapper = itemMapper;
    }

    public void registerItem(CreateItemRequest createItemRequest, UUID adminId) {
        assertValidItem(createItemRequest);
        assertItemNotPartOfDatabase(createItemRequest);
        assertValidUser(adminId);
        assertAuthorizedUser(adminId);

        itemRepository.registerItem(itemMapper.toItem(createItemRequest));

    }

    @Override
    public void updateItem(CreateItemRequest createItemRequest, UUID adminId, UUID itemId) {
        assertValidItem(createItemRequest);
        assertItemAllreadyPartOfDatabase(itemId);
        assertValidUser(adminId);
        assertAuthorizedUser(adminId);

        itemRepository.updateItem(itemMapper.toUpdatedItem(createItemRequest, itemId));
    }

    private void assertValidUser(UUID userId) {
        if (userRepository.findById(userId) == null) {
            throw new InvalidUserException("This account is not part of the database.");
        }

    }

    private void assertAuthorizedUser(UUID userId) {
        if (userRepository.findById(userId).getRole() != Role.ADMIN) {
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
    }

    private void assertValidItem(CreateItemRequest createItemRequest) {
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
