package com.switchfullywork.eurder.mappers;

import com.switchfullywork.eurder.domain.itemdto.ShortenedItemResponse;
import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.domain.itemdto.CreateItemGroupRequest;
import com.switchfullywork.eurder.domain.entity.item.ItemGroup;
import com.switchfullywork.eurder.domain.itemdto.ItemGroupResponse;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.entity.order.Order;
import com.switchfullywork.eurder.domain.orderdto.OrderResponse;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderMapper {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderMapper(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
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

        return new Order.Builder()
                .withUser(userRepository.findUserByUserId(createOrderRequest.getCustomerId()))
                .withListOfItemGroups(itemGroupList)
                .withTotalPrice(calculateTotalPricePerOrder(itemGroupList))
                .build();
    }

    public OrderResponse toOrderDTO(Order order) {
        List<ItemGroupResponse> itemGroupList = toItemGroupDTOList(order.getListOfItemGroups());

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .listOfItemGroups(itemGroupList)
                .totalPrice(calculateTotalPricePerOrder(order.getListOfItemGroups()))
                .build();
    }

    public List<ItemGroup> toItemGroupList(List<CreateItemGroupRequest> createItemGroupRequestList) {
        return createItemGroupRequestList.stream().map(this::toItemGroup).toList();
    }


    public List<ItemGroupResponse> toItemGroupDTOList(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream().map(this::toItemGroupDTO).toList();
    }

    public ItemGroup toItemGroup(CreateItemGroupRequest createItemGroupRequest) {
        return new ItemGroup.Builder()
                .withItem(itemRepository.findItemByItemId(createItemGroupRequest.getItemId()))
                .withAmount(createItemGroupRequest.getAmount())
                .withShippingDate(calculateShippingDate(createItemGroupRequest))
                .build();
    }

    public ItemGroupResponse toItemGroupDTO(ItemGroup itemGroup) {
        return ItemGroupResponse.builder()
                .shortenedItemResponse(toShortItemDTO(itemGroup))
                .amount(itemGroup.getAmount())
                .build();
    }

    public ShortenedItemResponse toShortItemDTO(ItemGroup itemGroup){
        return ShortenedItemResponse.builder()
                .name(itemGroup.getItem().getName())
                .price(itemGroup.getItem().getPrice())
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
                totalPrice += itemGroup.getItem().getPrice() * itemGroup.getAmount();

        }
        return totalPrice;
    }

    public LocalDate calculateShippingDate(CreateItemGroupRequest createItemGroupRequest) {
        LocalDate shippingDate = LocalDate.now();
        if (itemRepository.findItemByItemId(createItemGroupRequest.getItemId()).getAmountStock() > createItemGroupRequest.getAmount()) {
            return shippingDate.plusDays(1);
        }
        return shippingDate.plusDays(7);
    }

}
