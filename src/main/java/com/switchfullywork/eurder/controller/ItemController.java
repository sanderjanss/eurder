package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.itemdto.CreateItemRequest;
import com.switchfullywork.eurder.domain.itemdto.ItemResponse;
import com.switchfullywork.eurder.service.ItemService;
import com.switchfullywork.eurder.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ItemResponse itemResponse = itemService.registerItem(createItemRequest);
        logger.info("Item registered with id: " + itemResponse.getItemId());
    }

    @PutMapping(consumes = "application/json", path = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public void updateItem(@RequestBody CreateItemRequest createItemRequest, @PathVariable(value = "itemId") int itemId) {
        logger.info("Updating item with id: " + itemId);
        itemService.updateItem(createItemRequest, itemId);
        logger.info("Updated item with id: " + itemId);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.CUSTOMER)
    public List<ItemResponse> getAllItems(){
        logger.info("Asking up all items.");
        List<ItemResponse> itemResponses = itemService.getAllItems();
        logger.info(String.format("Retrieved %s item.", itemResponses.size()));
        return itemResponses;
    }
}
