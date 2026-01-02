

Spring-Boot-Transaction-Propogation-Mandatory-Example-:


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
        return saveOrder;

    }


    @Transactional(propagation = Propagation.REQUIRED)// This Txn t2
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)//THis is txn T3
    public Product updateProductDetails(Product product){
        //Till now, it's working as expected as able to store the order and save the product with stock updated.
        // Now we need to replicate the use case to understand the need of txn mgmt
        // forcefully throwing exception to simulate use of tx
        if(product.getPrice()> 5000){
            throw new RuntimeException("DB Crashed ....");
        }
        return inventoryRepository.save(product);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAuditDetails(Order order, String action){
        AuditLog auditLog = new AuditLog();
        auditLog.setOrderId(Long.valueOf(order.getId()));
        auditLog.setAction(action);
        auditLog.setTimestamp(LocalDateTime.now());

        //save the audit log
        auditLogRepository.save(auditLog);
    }

 @Transactional(propagation = Propagation.MANDATORY)
    public void validatePayment(Order order){
        //Assume Payment processing happens here
        boolean paymentSuccessful = false;
        // if payment fail then we log the payment failure in the mandatory transaction
        if(!paymentSuccessful) {
            AuditLog auditLog = new AuditLog();
            auditLog.setOrderId(Long.valueOf(order.getId()));
            auditLog.setAction("Payment Failed for Order");
            auditLog.setTimestamp(LocalDateTime.now());

//            if(order.getTotalPrice()>1000){
//                throw new RuntimeException("Error in payment validator");
//            }
            //save the audit log
            auditLogRepository.save(auditLog);
        }
    }



mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          2 |        7 |       35000 |
| 103 |          3 |        2 |       59.98 |
| 104 |          3 |        8 |      239.92 |
| 105 |          3 |       10 |       299.9 |
+-----+------------+----------+-------------+
5 rows in set (0.004 sec)

mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              8 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            180 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.001 sec)

mysql> select * from auditlog;
+----+----------+---------------------------+---------------------+
| id | order_id | action                    | timestamp           |
+----+----------+---------------------------+---------------------+
|  1 |      105 | Order Placement Succeeded | 2026-01-02 10:49:18 |
|  2 |      106 |  Order Placement Failed   | 2026-01-02 10:57:07 |
+----+----------+---------------------------+---------------------+
2 rows in set (0.001 sec)

mysql> 


{
  "id": 106,
  "productId": 3,
  "quantity": 2
}


mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          2 |        7 |       35000 |
| 103 |          3 |        2 |       59.98 |
| 104 |          3 |        8 |      239.92 |
| 105 |          3 |       10 |       299.9 |
| 106 |          3 |        2 |       59.98 |
+-----+------------+----------+-------------+
6 rows in set (0.003 sec)

mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              8 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            178 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.001 sec)

mysql> select * from auditlog;
+----+----------+---------------------------+---------------------+
| id | order_id | action                    | timestamp           |
+----+----------+---------------------------+---------------------+
|  1 |      105 | Order Placement Succeeded | 2026-01-02 10:49:18 |
|  2 |      106 |  Order Placement Failed   | 2026-01-02 10:57:07 |
|  3 |      106 | Order Placement Succeeded | 2026-01-02 14:00:06 |
|  4 |      106 | Payment Failed for Order  | 2026-01-02 14:00:06 |
+----+----------+---------------------------+---------------------+
4 rows in set (0.001 sec)




// MANDATORY : Require an existing transaction , if nothing found it will throw exception
   - It's participating in the existing txn without creating any new txn. If it doesn't find any existing txn then it will throw exception


2026-01-02T13:58:13.799+05:30  INFO 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-01-02T13:58:13.800+05:30  INFO 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-01-02T13:58:13.804+05:30  INFO 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 4 ms
2026-01-02T13:58:14.147+05:30  INFO 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-4] o.springdoc.api.AbstractOpenApiResource  : Init duration for springdoc-openapi is: 97 ms
2026-01-02T14:00:06.075+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.076+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T14:00:06.086+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@41320f06]
2026-01-02T14:00:06.091+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.091+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.141+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.141+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.142+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.142+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.155+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.156+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.156+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.156+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.157+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.157+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction, creating new transaction with name [com.starttohkar.handler.AuditLogHandler.logAuditDetails]
2026-01-02T14:00:06.157+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(365279225<open>)] for JPA transaction
2026-01-02T14:00:06.161+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@e6468ff]
2026-01-02T14:00:06.162+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(365279225<open>)] for JPA transaction
2026-01-02T14:00:06.162+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.183+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T14:00:06.183+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(365279225<open>)]
2026-01-02T14:00:06.201+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
2026-01-02T14:00:06.201+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
2026-01-02T14:00:06.201+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.201+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.202+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1746637953<open>)] for JPA transaction
2026-01-02T14:00:06.202+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:00:06.214+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T14:00:06.214+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1746637953<open>)]
2026-01-02T14:00:06.228+05:30 DEBUG 27258 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction
