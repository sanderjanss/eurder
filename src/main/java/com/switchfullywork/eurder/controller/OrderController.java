package com.switchfullywork.eurder.controller;

import com.switchfullywork.eurder.domain.orderdto.CreateOrderRequest;
import com.switchfullywork.eurder.domain.orderdto.OrderResponse;
import com.switchfullywork.eurder.domain.orderdto.ReportResponse;
import com.switchfullywork.eurder.service.OrderService;
import com.switchfullywork.eurder.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @SecurityGuard(SecurityGuard.ApiUserRole.CUSTOMER)
    public OrderResponse registerOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        logger.info("Registering Order.");
        OrderResponse orderResponse = orderService.registerOrder(createOrderRequest);
        logger.info("Order registered.");
        return orderResponse;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public List<OrderResponse> getAllOrders() {
        logger.info("Asking up all orders.");
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        logger.info("Retrieved all orders.");
        return orderResponses;
    }

    @GetMapping(produces = "application/json", path = "/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.CUSTOMER)
    public ReportResponse getReport(@PathVariable(value = "customerId") int customerId) {
        logger.info("Asking up report for customer id: " + customerId);
        ReportResponse reportResponse = orderService.getReport(customerId);
        logger.info("Retrieved report for customer id: " + customerId);
        return reportResponse;
    }

}
