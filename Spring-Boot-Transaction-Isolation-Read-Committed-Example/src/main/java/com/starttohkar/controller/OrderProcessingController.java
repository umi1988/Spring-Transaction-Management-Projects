package com.starttohkar.controller;

import com.starttohkar.entity.Order;
import com.starttohkar.service.OrderProcessingService;
import com.starttohkar.service.ProductService;
import com.starttohkar.service.isolation.ReadCommittedDemo;
import com.starttohkar.service.isolation.ReadUncommittedDemo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderProcessingController {

    private final OrderProcessingService orderProcessingService;
    private final ReadUncommittedDemo readUncommittedDemo;
    private final ReadCommittedDemo readCommittedDemo;

    public OrderProcessingController(OrderProcessingService orderProcessingService,
                                     ReadUncommittedDemo readUncommittedDemo,
                                     ReadCommittedDemo readCommittedDemo){
        this.orderProcessingService=orderProcessingService;
        this.readUncommittedDemo=readUncommittedDemo;
        this.readCommittedDemo=readCommittedDemo;
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

    @GetMapping("/isolation")
    public String testIsolation() throws InterruptedException {
       // readUncommittedDemo.testReadUncommitted(1);
        readCommittedDemo.testReadCommitted(1);
        return "SUCCESS";
    }
}
