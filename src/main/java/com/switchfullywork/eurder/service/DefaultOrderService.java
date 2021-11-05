package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.order.Order;
import com.switchfullywork.eurder.domain.itemdto.CreateItemGroupRequest;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.mappers.OrderMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, OrderMapper orderMapper, ItemRepository itemRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    public double registerOrder(CreateOrderRequest createOrderRequest) {
        assertValidOrderRequest(createOrderRequest);
        userService.assertValidUser(createOrderRequest.getCustomerId());

        for (CreateItemGroupRequest itemGroup : createOrderRequest.getListOfItemGroups()) {
            if (!itemRepository.contains(itemGroup.getItemId())) {
                throw new InvalidItemException("Not a valid Item");
            }
        }


        Order order = orderMapper.toOrder(createOrderRequest);
        orderRepository.registerOrder(order);
        return order.getTotalPrice();
    }

    @Override
    public ReportResponse getReport(UUID customerId) {
        userService.assertValidUser(customerId);
        return orderMapper.toReportDTO(orderRepository.getOrders(customerId));
    }

    public void assertValidOrderRequest(CreateOrderRequest createOrderRequest){
        if (createOrderRequest == null) {
            throw new InvalidUserException("This account is not part of the database.");
        }
    }
}
