package com.starttohkar.service;

import com.starttohkar.entity.Order;
import com.starttohkar.entity.Product;
import com.starttohkar.handler.InventoryHandler;
import com.starttohkar.handler.OrderHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService {

    private final InventoryHandler inventoryHandler;
    private final OrderHandler orderHandler;

    //constructor injection
    public OrderProcessingService(InventoryHandler inventoryHandler, OrderHandler orderHandler){
        this.inventoryHandler=inventoryHandler;
        this.orderHandler=orderHandler;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Order placeAnOrder(Order order){

        // get the product from inventory
        Product product = inventoryHandler.getProduct(order.getProductId());

        // validate stock availability
        validateStockAvailability(order, product);

        // update total price in order entity
        order.setTotalPrice(order.getQuantity() *  product.getPrice());

        // save the order
        Order saveOrder = orderHandler.saveOrder(order);

        // update stock in inventory
        updateInventoryStock(order, product);

        return saveOrder;

    }

    private static void validateStockAvailability(Order order, Product product) {
        if( order.getQuantity() > product.getStockQuantity()){
            throw new RuntimeException("Insufficient stock !!!");
        }
    }

    private void updateInventoryStock(Order order, Product product) {
        int availableStock = product.getStockQuantity() - order.getQuantity();
        product.setStockQuantity(availableStock);
        inventoryHandler.updateProductDetails(product);
    }
}
