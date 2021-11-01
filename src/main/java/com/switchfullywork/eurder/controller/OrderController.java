package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.order.CreateOrderDTO;
import com.switchfullywork.eurder.domain.order.Order;
import com.switchfullywork.eurder.domain.order.OrderDTO;
import com.switchfullywork.eurder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    public final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = "application/json", produces = "application/text")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        logger.info("Registering Order.");
        double totalPrice = orderService.registerOrder(createOrderDTO);
        logger.info("Order registered.");
        return "" + totalPrice;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrder(@RequestHeader(value = "customerId") UUID customerId){
        return orderService.getOrder(customerId);
    }

}
