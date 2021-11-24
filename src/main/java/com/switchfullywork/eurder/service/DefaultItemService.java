package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.item.Item;
import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.mappers.ItemMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultItemService implements ItemService {

    private final ItemRepository itemRepository;
    private final ValidationService validationService;
    private final ItemMapper itemMapper;

    @Autowired
    public DefaultItemService(ItemRepository itemRepository, ValidationService validationService, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.validationService = validationService;
        this.itemMapper = itemMapper;
    }

    public void registerItem(CreateItemRequest createItemRequest) {
        validationService.assertValidItemRequest(createItemRequest);
        validationService.assertItemNotPartOfDatabase(createItemRequest);

        Item item = itemMapper.toEntity(createItemRequest);
        itemRepository.save(item);
    }

    @Override
    public void updateItem(CreateItemRequest createItemRequest, int itemId) {
        validationService.assertValidItemRequest(createItemRequest);
        validationService.assertItemAllreadyPartOfDatabase(itemId);

        Item itemToUpdate = itemRepository.findItemByItemId(itemId);
        Item updatedItem = itemMapper.toEntity(createItemRequest);
        itemToUpdate.updateItem(updatedItem);
    }
}
