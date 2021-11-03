package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.domain.itemdto.CreateItemGroupRequest;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.entity.order.Order;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.InvalidOrderException;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.mappers.OrderMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.OrderRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public double registerOrder(CreateOrderRequest createOrderRequest) {
        if (createOrderRequest == null) {
            throw new InvalidOrderException("Not a valid Order.");
        }

        if (userRepository.findById(createOrderRequest.getCustomerId()) == null) {
            throw new InvalidUserException("Not a valid User.");
        }

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
        if(userRepository.findById(customerId) == null){
            throw new InvalidUserException("Not a valid user.");
        }
        return orderMapper.toReportDTO(orderRepository.getOrders(customerId));
    }
}
