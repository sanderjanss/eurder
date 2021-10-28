package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.item.CreateItemDTO;
import com.switchfullywork.eurder.domain.item.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toItem(CreateItemDTO createItemDTO){
        var item = new Item.ItemBuilder();
        return item
                .setName(createItemDTO.getName())
                .setDescription(createItemDTO.getDescription())
                .setPrice(createItemDTO.getPrice())
                .setAmountStock(createItemDTO.getAmountStock())
                .build();
    }


}
