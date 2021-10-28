package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.item.CreateItemDTO;
import com.switchfullywork.eurder.domain.user.Role;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.exceptions.NoAuthorizationException;
import com.switchfullywork.eurder.mappers.ItemMapper;
import com.switchfullywork.eurder.repository.DefaultItemRepository;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.apache.catalina.User;
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

    public void registerItem(CreateItemDTO createItemDTO, UUID adminId){
        assertValidItem(createItemDTO);
        assertValidUser(adminId);
        assertAuthorizedUser(adminId);
        itemRepository.registerItem(itemMapper.toItem(createItemDTO));
    }

    private void assertValidUser(UUID userId){
        if(userRepository.findById(userId) == null){
            throw new InvalidUserException("This account is not part of the database.");
        }
    }
    private void assertAuthorizedUser(UUID userId){
        if(userRepository.findById(userId).getRole() != Role.ADMIN){
            throw new NoAuthorizationException("You are not authorized to do this action.");
        }
    }
    private void assertValidItem(CreateItemDTO createItemDTO){
        if(createItemDTO == null){
            throw new InvalidItemException("Not a valid item.");
        }
    }

}
