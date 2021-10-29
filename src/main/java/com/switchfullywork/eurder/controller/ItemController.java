package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.item.CreateItemDTO;
import com.switchfullywork.eurder.service.ItemService;
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
    public void registerItem(@RequestBody CreateItemDTO createItemDTO, @RequestHeader(name = "adminId") UUID adminId) {
        logger.info("Registering item.");
        itemService.registerItem(createItemDTO, adminId);
        logger.info("Item registered.");
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@RequestBody CreateItemDTO createItemDTO, @RequestHeader(name = "adminId") UUID adminId, @RequestParam(value = "itemId") UUID itemId) {
        logger.info("Updating item.");
        itemService.updateItem(createItemDTO, adminId, itemId);
        logger.info("Updated item.");
    }
}
