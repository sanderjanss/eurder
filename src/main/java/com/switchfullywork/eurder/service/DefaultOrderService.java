package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.item.CreateItemGroupDTO;
import com.switchfullywork.eurder.domain.order.CreateOrderDTO;
import com.switchfullywork.eurder.exceptions.InvalidItemException;
import com.switchfullywork.eurder.exceptions.InvalidOrderException;
import com.switchfullywork.eurder.exceptions.InvalidUserException;
import com.switchfullywork.eurder.mappers.OrderMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.OrderRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void registerOrder(CreateOrderDTO createOrderDTO) {
        if (createOrderDTO == null) {
            throw new InvalidOrderException("Not a valid Order.");
        }

        if (userRepository.findById(createOrderDTO.getCustomerId()) == null) {
            throw new InvalidUserException("Not a valid User.");
        }
        for (CreateItemGroupDTO itemGroup : createOrderDTO.getListOfItemGroups()) {
            if (!itemRepository.contains(itemGroup.getItemId())) {
                throw new InvalidItemException("Not a valid Item");
            }
        }

        orderRepository.registerOrder(orderMapper.toOrder(createOrderDTO));
    }
}
