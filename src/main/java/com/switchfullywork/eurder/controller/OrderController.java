package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.order.CreateOrderDTO;
import com.switchfullywork.eurder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerOrder(@RequestBody CreateOrderDTO createOrderDTO){
        orderService.registerOrder(createOrderDTO);
    }
}
