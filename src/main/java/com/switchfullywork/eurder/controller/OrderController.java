package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
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
    public String registerOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        logger.info("Registering Order.");
        double totalPrice = orderService.registerOrder(createOrderRequest);
        logger.info("Order registered.");
        return "" + totalPrice;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse getOrder(@RequestHeader(value = "customerId") UUID customerId) {
        return orderService.getReport(customerId);
    }

}
