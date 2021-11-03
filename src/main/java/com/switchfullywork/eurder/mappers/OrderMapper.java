package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.item.CreateItemGroupDTO;
import com.switchfullywork.eurder.domain.item.ItemGroup;
import com.switchfullywork.eurder.domain.item.ItemGroupDTO;
import com.switchfullywork.eurder.domain.order.CreateOrderDTO;
import com.switchfullywork.eurder.domain.order.Order;
import com.switchfullywork.eurder.domain.order.OrderDTO;
import com.switchfullywork.eurder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderMapper {

    private final ItemRepository itemRepository;

    @Autowired
    public OrderMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Order toOrder(CreateOrderDTO createOrderDTO) {
        List<ItemGroup> itemGroupList = toItemGroupList(createOrderDTO.getListOfItemGroups());

        return new Order.OrderBuilder()
                .setCustomerId(createOrderDTO.getCustomerId())
                .setListOfItemGroups(itemGroupList)
                .setTotalPrice(calculateTotalPrice(itemGroupList))
                .build();
    }

    public OrderDTO toOrderDTO(Order order) {
        List<ItemGroup> itemGroupList = order.getListOfItemGroups();

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .listOfItemGroups(itemGroupList)
                .totalPrice(calculateTotalPrice(itemGroupList))
                .build();
    }

    public List<ItemGroup> toItemGroupList(List<CreateItemGroupDTO> createItemGroupDTOList) {
        return createItemGroupDTOList.stream().map(this::toItemGroup).toList();
    }

    public List<ItemGroupDTO> toItemGroupDTOList(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream().map(this::toItemGroupDTO).toList();
    }

    public ItemGroup toItemGroup(CreateItemGroupDTO createItemGroupDTO) {
        return new ItemGroup.ItemGroupBuilder()
                .setItemid(createItemGroupDTO.getItemId())
                .setAmount(createItemGroupDTO.getAmount())
                .setShippingDate(calculateShippingDate(createItemGroupDTO))
                .build();
    }

    public ItemGroupDTO toItemGroupDTO(ItemGroup itemGroup) {
        return ItemGroupDTO.builder()
                .itemGroupId(itemGroup.getItemId())
                .amount(itemGroup.getAmount())
                .shippingDate(itemGroup.getShippingDate())
                .build();
    }


    public double calculateTotalPrice(List<ItemGroup> itemGroupList) {
        double totalPrice = 0;
        for (ItemGroup itemGroup : itemGroupList) {
            if (itemRepository.contains(itemGroup.getItemId())) {
                totalPrice += itemRepository.getById(itemGroup.getItemId()).getPrice() * itemGroup.getAmount();
            }
        }
        return totalPrice;
    }

    public LocalDate calculateShippingDate(CreateItemGroupDTO createItemGroupDTO) {
        if (itemRepository.getById(createItemGroupDTO.getItemId()).getAmountStock() > createItemGroupDTO.getAmount()) {
            return createItemGroupDTO.getShippingDate().plusDays(1);
        }
        return createItemGroupDTO.getShippingDate().plusDays(7);
    }


}
