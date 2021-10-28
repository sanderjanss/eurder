package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.item.CreateItemDTO;
import com.switchfullywork.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerItem(@RequestBody CreateItemDTO createItemDTO, UUID adminId){
        itemService.registerItem(createItemDTO, adminId);
    }
}
