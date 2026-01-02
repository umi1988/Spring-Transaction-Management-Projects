
Spring-Boot-Transaction-Propogation-Required-Example-:

 @Transactional(propagation = Propagation.REQUIRED)

 Propagation.REQUIRED ->  meaning join an existing transaction or create a new one if not exist.

In this below methid we are performing txn. This placeAnOrder method is started with Outer Txn T1.  saveOrder and updateInventoryStock method hv also the same Propagation.REQUIRED. These inner txn t2 and t3 are being invoked under Outer Txn T1.
 @Transactional(propagation = Propagation.REQUIRED)
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
   

    Now with  Propagation.REQUIRED , it will not create a inner txn as we already exist outer txn. we can validate in the logs and confirm this use case.


    mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          2 |        7 |       35000 |
+-----+------------+----------+-------------+
2 rows in set (0.001 sec)

mysql>
mysql>
mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              8 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            200 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.003 sec)




POST Api used:-

{
  "id": 103,
  "productId": 3,
  "quantity": 2
}


In the db values are updated accordingly

mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          2 |        7 |       35000 |
| 103 |          3 |        2 |       59.98 |
+-----+------------+----------+-------------+
3 rows in set (0.001 sec)


mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              8 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            198 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.003 sec)



As we used Propagation.REQUIRED, we are creating only one txn and others methods are using the same outer txn. Verified in below logs:-

2026-01-02T09:35:29.528+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1555711327<open>)] for JPA transaction
2026-01-02T09:35:29.528+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T09:35:29.537+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@65f30645]
2026-01-02T09:35:29.540+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1555711327<open>)] for JPA transaction
2026-01-02T09:35:29.540+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T09:35:29.600+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1555711327<open>)] for JPA transaction
2026-01-02T09:35:29.600+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T09:35:29.601+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1555711327<open>)] for JPA transaction
2026-01-02T09:35:29.601+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T09:35:29.611+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1555711327<open>)] for JPA transaction
2026-01-02T09:35:29.611+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T09:35:29.611+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1555711327<open>)] for JPA transaction
2026-01-02T09:35:29.611+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T09:35:29.611+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T09:35:29.612+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1555711327<open>)]
2026-01-02T09:35:29.645+05:30 DEBUG 20292 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction



-> When we wants everything failed or everything succeed.
-> If we have the use case like if there is any failure, we don't want to keep the unused data rather we will revert everything. Then this Propagation.REQUIRED is needed.
