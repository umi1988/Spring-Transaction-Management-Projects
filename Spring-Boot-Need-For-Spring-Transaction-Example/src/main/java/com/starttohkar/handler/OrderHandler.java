package com.starttohkar.handler;

import com.starttohkar.entity.Order;
import com.starttohkar.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderHandler {

    private final OrderRepository orderRepository;

    //constructor injection
    public OrderHandler(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }


}
