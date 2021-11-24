package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.item.Item;
import com.switchfullywork.eurder.domain.itemdto.CreateItemGroupRequest;
import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.userdto.CreateUserRequest;
import com.switchfullywork.eurder.exceptions.*;
import com.switchfullywork.eurder.mappers.ItemMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final UserRepository userRepository;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public ValidationService(UserRepository userRepository, ItemMapper itemMapper, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public void assertValidUser(int userId) {
        if (userRepository.findUserByUserId(userId) == null) {
            throw new InvalidUserException();
        }
    }

    public void assertValidUserRequest(CreateUserRequest createUserRequest){
        if (createUserRequest == null) {
            throw new InvalidUserException();
        }
    }

    public void assertValidOrderRequest(CreateOrderRequest createOrderRequest){
        if (createOrderRequest == null) {
            throw new InvalidOrderException();
        }
    }

    public void assertValidItemRequest(CreateItemRequest createItemRequest){
        if (createItemRequest == null) {
            throw new InvalidItemException();
        }
    }

    public void assertItemNotPartOfDatabase(CreateItemRequest createItemRequest) {
        Item item = itemMapper.toItem(createItemRequest);
        if (itemRepository.findItemByName(item.getName()) != null) {
            throw new ItemAllreadyExistsException("This item is allready registered.");
        }
    }

    public void assertItemAllreadyPartOfDatabase(int itemId) {
        if (itemRepository.findItemByItemId(itemId) == null) {
            throw new InvalidItemException("This item is not part of the database.");
        }
    }

    public void assertValidItemGroupRequest(CreateOrderRequest createOrderRequest){
        for (CreateItemGroupRequest itemGroup : createOrderRequest.getListOfItemGroups()) {
            if (itemRepository.findItemByItemId(itemGroup.getItemId()) == null) {
                throw new InvalidItemException();
            }
        }
    }
}
