package com.starttohkar.service;

import com.starttohkar.entity.Order;
import com.starttohkar.entity.Product;
import com.starttohkar.handler.AuditLogHandler;
import com.starttohkar.handler.InventoryHandler;
import com.starttohkar.handler.OrderHandler;
import com.starttohkar.handler.PaymentValidatorHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService {

    private final InventoryHandler inventoryHandler;
    private final OrderHandler orderHandler;
    private final AuditLogHandler auditLogHandler;
    private final PaymentValidatorHandler paymentValidatorHandler;

    //constructor injection
    public OrderProcessingService(InventoryHandler inventoryHandler, OrderHandler orderHandler,AuditLogHandler auditLogHandler, PaymentValidatorHandler paymentValidatorHandler){
        this.inventoryHandler=inventoryHandler;
        this.orderHandler=orderHandler;
        this.auditLogHandler=auditLogHandler;
        this.paymentValidatorHandler=paymentValidatorHandler;
    }

    // Propagation.REQUIRED : join an existing transaction or create a new one if not exist
    // Propagation.REQUIRED_NEW ->  meaning Always create a new transaction, suspending if any existing transaction until execution completes.
    // MANDATORY : Require an existing transaction , if nothing found it will throw exception
    // This is txn T1 - //outer tx
    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeAnOrder(Order order){
        Order saveOrder= null;

        // get the product from inventory
        Product product = inventoryHandler.getProduct(order.getProductId());

        // validate stock availability
        validateStockAvailability(order, product);

        // update total price in order entity
        order.setTotalPrice(order.getQuantity() *  product.getPrice());

        // save the order
       try {
            saveOrder = orderHandler.saveOrder(order);

           // update stock in inventory
           updateInventoryStock(order, product);

           // Audit log
           auditLogHandler.logAuditDetails(order ,"Order Placement Succeeded");

       }catch (Exception e){
           auditLogHandler.logAuditDetails(order, " Order Placement Failed");
       }
       // validate the payment
        paymentValidatorHandler.validatePayment(order);

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
