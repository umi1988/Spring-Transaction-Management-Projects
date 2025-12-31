
Request1 for smartphone product - 

{
  "id": 102,
  "productId": 2,
  "quantity": 7
}


mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
+-----+------------+----------+-------------+
1 row in set (0.001 sec)

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

mysql> 
mysql> 
mysql> 
mysql> 
mysql> 
mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          2 |        7 |       35000 |
+-----+------------+----------+-------------+
2 rows in set (0.001 sec)

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
9 rows in set (0.001 sec)

mysql> 




REQUEST 2> for laptop product

{
  "id": 103,
  "productId": 1,
  "quantity": 5
}


mysql> select * from orders;
+-----+------------+----------+-------------+
| id  | product_id | quantity | total_price |
+-----+------------+----------+-------------+
| 101 |          1 |        2 |       50000 |
| 102 |          2 |        7 |       35000 |
+-----+------------+----------+-------------+
2 rows in set (0.001 sec)

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
9 rows in set (0.001 sec)

mysql> 


No changes updated in the db as the failure occurred as DB crashed. If any flow failed then complete rollback will happen as we are using

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)





