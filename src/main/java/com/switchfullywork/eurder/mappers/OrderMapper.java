package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.domain.itemdto.CreateItemGroupRequest;
import com.switchfullywork.eurder.domain.entity.item.ItemGroup;
import com.switchfullywork.eurder.domain.itemdto.ItemGroupResponse;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.entity.order.Order;
import com.switchfullywork.eurder.domain.orderdto.OrderResponse;
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

    public ReportResponse toReportDTO(List<Order> orderList){
        List<OrderResponse> orderResponseList = toOrderDTOList(orderList);
        return ReportResponse.builder()
                .listOfOrders(orderResponseList)
                .totalPriceAllOrders(calculateTotalPriceReport(orderResponseList))
                .build();
    }

    public List<OrderResponse> toOrderDTOList(List<Order> orderList){
        return orderList.stream()
                .map(this::toOrderDTO)
                .toList();
    }

    public Order toOrder(CreateOrderRequest createOrderRequest) {
        List<ItemGroup> itemGroupList = toItemGroupList(createOrderRequest.getListOfItemGroups());

        return new Order.OrderBuilder()
                .setCustomerId(createOrderRequest.getCustomerId())
                .setListOfItemGroups(itemGroupList)
                .setTotalPrice(calculateTotalPricePerOrder(itemGroupList))
                .build();
    }

    public OrderResponse toOrderDTO(Order order) {
        List<ItemGroup> itemGroupList = order.getListOfItemGroups();

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .listOfItemGroups(itemGroupList)
                .totalPrice(calculateTotalPricePerOrder(itemGroupList))
                .build();
    }

    public List<ItemGroup> toItemGroupList(List<CreateItemGroupRequest> createItemGroupRequestList) {
        return createItemGroupRequestList.stream().map(this::toItemGroup).toList();
    }

    public List<ItemGroupResponse> toItemGroupDTOList(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream().map(this::toItemGroupDTO).toList();
    }

    public ItemGroup toItemGroup(CreateItemGroupRequest createItemGroupRequest) {
        return new ItemGroup.ItemGroupBuilder()
                .setItemid(createItemGroupRequest.getItemId())
                .setAmount(createItemGroupRequest.getAmount())
                .setShippingDate(calculateShippingDate(createItemGroupRequest))
                .build();
    }

    public ItemGroupResponse toItemGroupDTO(ItemGroup itemGroup) {
        return ItemGroupResponse.builder()
                .itemGroupId(itemGroup.getItemId())
                .amount(itemGroup.getAmount())
                .shippingDate(itemGroup.getShippingDate())
                .build();
    }

    ///////////////////////////////////////////////////:

    public double calculateTotalPriceReport(List<OrderResponse> orderResponseList){
        double totalPrice = 0;
        for(OrderResponse order: orderResponseList){
            totalPrice += order.getTotalPrice();
        }
        return totalPrice;
    }

    public double calculateTotalPricePerOrder(List<ItemGroup> itemGroupList) {
        double totalPrice = 0;
        for (ItemGroup itemGroup : itemGroupList) {
            if (itemRepository.contains(itemGroup.getItemId())) {
                totalPrice += itemRepository.getById(itemGroup.getItemId()).getPrice() * itemGroup.getAmount();
            }
        }
        return totalPrice;
    }

    public LocalDate calculateShippingDate(CreateItemGroupRequest createItemGroupRequest) {
        if (itemRepository.getById(createItemGroupRequest.getItemId()).getAmountStock() > createItemGroupRequest.getAmount()) {
            return createItemGroupRequest.getShippingDate().plusDays(1);
        }
        return createItemGroupRequest.getShippingDate().plusDays(7);
    }

}
