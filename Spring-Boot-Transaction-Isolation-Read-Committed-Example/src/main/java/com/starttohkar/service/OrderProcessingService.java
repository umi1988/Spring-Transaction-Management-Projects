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
    private final ProductRecommendationHandler productRecommendationHandler;

    //constructor injection
    public OrderProcessingService(InventoryHandler inventoryHandler, OrderHandler orderHandler,AuditLogHandler auditLogHandler,
                                  PaymentValidatorHandler paymentValidatorHandler, NotificationHandler notificationHandler,
                                  ProductRecommendationHandler productRecommendationHandler){
        this.inventoryHandler=inventoryHandler;
        this.orderHandler=orderHandler;
        this.auditLogHandler=auditLogHandler;
        this.paymentValidatorHandler=paymentValidatorHandler;
        this.notificationHandler=notificationHandler;
        this.productRecommendationHandler=productRecommendationHandler;
    }

    // Propagation.REQUIRED : join an existing transaction or create a new one if not exist
    // Propagation.REQUIRED_NEW ->  meaning Always create a new transaction, suspending if any existing transaction until execution completes.
    // MANDATORY : Require an existing transaction , if nothing found it will throw exception
    // NEVER : Ensure the method will run without transaction , throw an exception if found any
    // NOT_SUPPORTED : Execute method without transaction, suspending any active transaction
    // SUPPORTS : Supports if there is any active transaction , if not there then also it will execute without transaction
    // NESTED : Execute within a nested transaction, allowing nested transaction
    //            to rollback independently if there is any exception without impacting outer transaction

    // This is txn T1 -
    // outer tx
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
          //  paymentValidatorHandler.validatePayment(order);

       // productRecommendationHandler.getRecommendations();//this will execute in happy and sad scenario. It will execute, it doesn't required any txn, if any txn will be there then i will suspend it.
       // getCustomerDetails();// this will support it if there is any active txn, if it's not available then it will execute without txn

        return saveOrder;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void getCustomerDetails() {
        System.out.println("Customer details fetched !!!!!");
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
