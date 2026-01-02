package com.starttohkar.handler;

import com.starttohkar.entity.Order;
import com.starttohkar.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderHandler {

    private final OrderRepository orderRepository;

    //constructor injection
    public OrderHandler(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)// This Txn t2
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }


}
