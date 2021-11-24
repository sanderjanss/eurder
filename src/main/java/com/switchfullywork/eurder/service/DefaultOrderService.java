package com.switchfullywork.eurder.service;

import com.switchfullywork.eurder.domain.entity.order.Order;
import com.switchfullywork.eurder.domain.entity.user.User;
import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.orderdto.OrderResponse;
import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.mappers.OrderMapper;
import com.switchfullywork.eurder.repository.ItemRepository;
import com.switchfullywork.eurder.repository.OrderRepository;
import com.switchfullywork.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final ItemRepository itemRepository;
    private final ValidationService validationService;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper, ItemRepository itemRepository, ValidationService validationService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.itemRepository = itemRepository;
        this.validationService = validationService;
    }

    public OrderResponse registerOrder(CreateOrderRequest createOrderRequest) {
        validationService.assertValidOrderRequest(createOrderRequest);
        validationService.assertValidUser(createOrderRequest.getCustomerId());
        validationService.assertValidItemGroupRequest(createOrderRequest);

        Order order = orderMapper.toOrder(createOrderRequest);
        orderRepository.save(order);
        return orderMapper.toOrderDto(order);
    }

    @Override
    public ReportResponse getReport(int customerId) {
        validationService.assertValidUser(customerId);
        User user = userRepository.findUserByUserId(customerId);
        return orderMapper.toReportDto(orderRepository.findAllByUser(user));
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderMapper.toOrderDtoList(orderRepository.findAll());
    }
}
