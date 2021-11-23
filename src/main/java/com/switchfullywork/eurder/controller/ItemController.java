package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.service.ItemService;
import com.switchfullywork.eurder.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    public final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public void registerItem(@RequestBody CreateItemRequest createItemRequest) {
        logger.info("Registering item.");
        itemService.registerItem(createItemRequest);
        logger.info("Item registered.");
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public void updateItem(@RequestBody CreateItemRequest createItemRequest, @RequestParam(value = "itemId") int itemId) {
        logger.info("Updating item.");
        itemService.updateItem(createItemRequest, itemId);
        logger.info("Updated item.");
    }
}
