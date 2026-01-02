package com.starttohkar.controller;

import com.starttohkar.entity.Order;
import com.starttohkar.service.OrderProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderProcessingController {

    private final OrderProcessingService orderProcessingService;

    public OrderProcessingController(OrderProcessingService orderProcessingService){
        this.orderProcessingService=orderProcessingService;
    }

    /**
     * Api to place an order
     * @param order
     * @return
     */
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderProcessingService.placeAnOrder(order));
    }
}
