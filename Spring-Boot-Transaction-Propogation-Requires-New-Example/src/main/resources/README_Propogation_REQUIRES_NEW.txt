
Spring-Boot-Transaction-Propogation-Required-New-Example-:

 @Transactional(propagation = Propagation.REQUIRES_NEW)

 Propagation.REQUIRES_NEW ->  meaning join an existing transaction or create a new one if not exist.

In this below methid we are performing txn. This placeAnOrder method is started with Outer Txn T1.  saveOrder and updateInventoryStock method hv also the same Propagation.REQUIRED. These inner txn t2 and t3 are being invoked under Outer Txn T1. Only Audit log data we need to persisted in db. Rest all hv to use the same outer txn. But in audit log flow we have REQUIRES_NEW txn
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

    Now with  Propagation.REQUIRES_NEW , it will always create a inner txns as it will suspend the existing one if it's running till it's execution. we can validate in the logs and confirm this use case.


POSITIVE USECASE:-

mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              8 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            190 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.003 sec)

mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          2 |        7 |       35000 |
| 103 |          3 |        2 |       59.98 |
| 104 |          3 |        8 |      239.92 |
+-----+------------+----------+-------------+
4 rows in set (0.001 sec)

mysql> select * from auditlog;
Empty set (0.006 sec)





POST Api used:-

{
  "id": 105,
  "productId": 3,
  "quantity": 10
}


In the db values are updated accordingly

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
5 rows in set (0.001 sec)

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
+----+----------+---------------------------+---------------------+
1 row in set (0.001 sec)

mysql> 


---> Resuming suspended transaction after completion of inner transaction -- This need to understand like once the inner txn completed , it resumed the main txn that is outer txn(place an order txn)  



As we used Propagation.REQUIRES_NEW, we are always creating txn and suspending outers txn. We used REQUIRES_NEW in onlu auditlog flow. Verified in below logs:-

2026-01-02T10:48:34.358+05:30  INFO 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-01-02T10:48:34.358+05:30  INFO 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-01-02T10:48:34.363+05:30  INFO 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 5 ms
2026-01-02T10:48:34.669+05:30  INFO 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-4] o.springdoc.api.AbstractOpenApiResource  : Init duration for springdoc-openapi is: 100 ms
2026-01-02T10:49:17.563+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(100812179<open>)] for JPA transaction
2026-01-02T10:49:17.563+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T10:49:17.570+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@4a8c4ce]
2026-01-02T10:49:17.573+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(100812179<open>)] for JPA transaction
2026-01-02T10:49:17.573+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:49:17.611+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(100812179<open>)] for JPA transaction
2026-01-02T10:49:17.611+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:49:17.611+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(100812179<open>)] for JPA transaction
2026-01-02T10:49:17.611+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:49:17.621+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(100812179<open>)] for JPA transaction
2026-01-02T10:49:17.621+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:49:17.621+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(100812179<open>)] for JPA transaction
2026-01-02T10:49:17.621+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:49:17.621+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(100812179<open>)] for JPA transaction
2026-01-02T10:49:17.621+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction, creating new transaction with name [com.starttohkar.handler.AuditLogHandler.logAuditDetails]
2026-01-02T10:49:17.622+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1210088276<open>)] for JPA transaction
2026-01-02T10:49:17.625+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@59b2fa]
2026-01-02T10:49:17.626+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1210088276<open>)] for JPA transaction
2026-01-02T10:49:17.626+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:49:17.640+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T10:49:17.641+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1210088276<open>)]
2026-01-02T10:49:17.652+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
2026-01-02T10:49:17.653+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
2026-01-02T10:49:17.653+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T10:49:17.653+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(100812179<open>)]
2026-01-02T10:49:17.667+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-7] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction




-> When we wants something failed or something succeed.
-> If we have the use case like if there is any failure, we want to keep the unused data. Then this Propagation.REQUIRES_NEW is needed.





NEGATIVE USECASE:-


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
5 rows in set (0.001 sec)

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
+----+----------+---------------------------+---------------------+
1 row in set (0.001 sec)

mysql> 




{
  "id": 106,
  "productId": 1,
  "quantity": 1
}

{
  "timestamp": "2026-01-02T05:27:07.266Z",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/api/orders"
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
5 rows in set (0.002 sec)

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


---> Resuming suspended transaction after completion of inner transaction -- This need to understand like once the inner txn completed , it resumed the main txn that is outer txn(place an order txn)  



2026-01-02T10:57:07.175+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2012169478<open>)] for JPA transaction
2026-01-02T10:57:07.177+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T10:57:07.183+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@7928b58b]
2026-01-02T10:57:07.184+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2012169478<open>)] for JPA transaction
2026-01-02T10:57:07.184+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:57:07.196+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2012169478<open>)] for JPA transaction
2026-01-02T10:57:07.197+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:57:07.197+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2012169478<open>)] for JPA transaction
2026-01-02T10:57:07.197+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:57:07.203+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2012169478<open>)] for JPA transaction
2026-01-02T10:57:07.203+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:57:07.205+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Participating transaction failed - marking existing transaction as rollback-only
2026-01-02T10:57:07.206+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Setting JPA transaction on EntityManager [SessionImpl(2012169478<open>)] rollback-only
2026-01-02T10:57:07.206+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2012169478<open>)] for JPA transaction
2026-01-02T10:57:07.206+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction, creating new transaction with name [com.starttohkar.handler.AuditLogHandler.logAuditDetails]
2026-01-02T10:57:07.207+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(360599595<open>)] for JPA transaction
2026-01-02T10:57:07.211+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@2783181d]
2026-01-02T10:57:07.212+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(360599595<open>)] for JPA transaction
2026-01-02T10:57:07.212+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T10:57:07.220+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T10:57:07.220+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(360599595<open>)]
2026-01-02T10:57:07.227+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
2026-01-02T10:57:07.227+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
2026-01-02T10:57:07.227+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T10:57:07.228+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(2012169478<open>)]
2026-01-02T10:57:07.237+05:30 DEBUG 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction
2026-01-02T10:57:07.243+05:30 ERROR 23726 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-9] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only] with root cause

org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:803) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:757) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:684) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:406) ~[spring-tx-7.0.2.jar:7.0.2]
