package com.starttohkar.service;

import com.starttohkar.entity.Order;
import com.starttohkar.entity.Product;
import com.starttohkar.handler.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService {

    private final InventoryHandler inventoryHandler;
    private final OrderHandler orderHandler;
    private final AuditLogHandler auditLogHandler;
    private final PaymentValidatorHandler paymentValidatorHandler;
    private final NotificationHandler notificationHandler;

    //constructor injection
    public OrderProcessingService(InventoryHandler inventoryHandler, OrderHandler orderHandler,AuditLogHandler auditLogHandler,
                                  PaymentValidatorHandler paymentValidatorHandler, NotificationHandler notificationHandler){
        this.inventoryHandler=inventoryHandler;
        this.orderHandler=orderHandler;
        this.auditLogHandler=auditLogHandler;
        this.paymentValidatorHandler=paymentValidatorHandler;
        this.notificationHandler=notificationHandler;
    }

    // Propagation.REQUIRED : join an existing transaction or create a new one if not exist
    // Propagation.REQUIRED_NEW ->  meaning Always create a new transaction, suspending if any existing transaction until execution completes.
    // MANDATORY : Require an existing transaction , if nothing found it will throw exception
    // NEVER : Ensure the method will run without transaction , throw an exception if found any
    
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
       //notificationHandler.sendOrderConfirmationNotification(order);// Here it will fail as we hv txn created and executing in this method

       // validate the payment
        paymentValidatorHandler.validatePayment(order);

        return saveOrder;

    }

    // call this in a separate flow
    //Here it will work as we hv txn created and executed in placeAnOrder method
    // Call this method after placeAnOrder is successfully completed
    public void processOrder(Order order) {
        // Step 1: Place the order
        Order savedOrder = placeAnOrder(order);

        // Step 2: Send notification (non-transactional)
        notificationHandler.sendOrderConfirmationNotification(order);
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
