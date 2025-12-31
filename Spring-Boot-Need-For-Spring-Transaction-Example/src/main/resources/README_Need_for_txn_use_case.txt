
Need spring txn to fix the data inconsistency in different db operations


reuest 1-

{
  "id": 101,
  "productId": 1,
  "quantity": 2
}

product -
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              8 |
|  2 | SmartPhone          |   5000 |             12 |
|  3 | Wireless Mouse      |  29.99 |            200 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+



orders - 
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
+-----+------------+----------+-------------+

here order db updated but product also updated.


select * from orders;
select * from product;


delete from orders where id = 102;
update product set stock_quantity=8 where id=1;

request 2-


{
  "id": 102,
  "productId": 1,
  "quantity": 5
}

mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          1 |        5 |      125000 |
+-----+------------+----------+-------------+
2 rows in set (0.001 sec)

mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              8 |
|  2 | SmartPhone          |   5000 |             12 |
|  3 | Wireless Mouse      |  29.99 |            200 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.001 sec)



In the current scenario, the order was successfully inserted into the `orders` table (ID 102), but due to a failure, the corresponding update in the `products` table did not occur.

As a result:
- The `stock_quantity` for product ID 1 remains unchanged (should have been reduced by 5).
- An orphan order entry (ID 102) now exists in the `orders` table without a matching stock deduction.

This violates data consistency.

**Expected behavior**:
If any part of the transaction fails (e.g., updating product stock), the entire operation should be rolled back. This means:
- No entry should be inserted into the `orders` table.
- No changes should be made to the `products` table.

**Solution**:
We must ensure that both operations (inserting the order and updating stock quantity) are executed within a single database transaction using proper transaction management (e.g., `@Transactional` in Spring Boot).

This way, if the stock update fails, the order insertion will automatically be rolled back, maintaining data integrity across tables.












