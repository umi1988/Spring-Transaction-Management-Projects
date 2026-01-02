


Spring-Boot-Transaction-Isolation-Read-Uncommitted-Example-:



without roll back



mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |             50 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            174 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.004 sec)




2026-01-02T17:07:33.736+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.updateStock]: PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED
2026-01-02T17:07:33.739+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1013969488<open>)] for JPA transaction
2026-01-02T17:07:33.759+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@73fa98c2]
2026-01-02T17:07:33.767+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1013969488<open>)] for JPA transaction
2026-01-02T17:07:33.767+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T17:07:33.817+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1013969488<open>)] for JPA transaction
2026-01-02T17:07:33.817+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction A: Stock updated to 5
2026-01-02T17:07:35.724+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.checkStock]: PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED
2026-01-02T17:07:35.725+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(365369647<open>)] for JPA transaction
2026-01-02T17:07:35.734+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@1b00f5e7]
2026-01-02T17:07:35.737+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(365369647<open>)] for JPA transaction
2026-01-02T17:07:35.737+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction B: Read stock as 5
2026-01-02T17:07:35.745+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T17:07:35.746+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(365369647<open>)]
2026-01-02T17:07:35.759+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
Stock read by Transaction B: 5
Transaction A: Committed the update
2026-01-02T17:07:38.844+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T17:07:38.845+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1013969488<open>)]
2026-01-02T17:07:38.857+05:30 DEBUG 35636 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction




mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |              5 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            174 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.004 sec)



with rollback

added these changes in product service:- 

System.out.println("Transaction A: Rolling back the update");
TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//explicit rollback




mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |             50 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            174 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.004 sec)


2026-01-02T17:15:35.782+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.updateStock]: PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED
2026-01-02T17:15:35.782+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1891605271<open>)] for JPA transaction
2026-01-02T17:15:35.791+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@7e787b70]
2026-01-02T17:15:35.792+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1891605271<open>)] for JPA transaction
2026-01-02T17:15:35.792+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T17:15:35.798+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1891605271<open>)] for JPA transaction
2026-01-02T17:15:35.798+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction A: Stock updated to 5
2026-01-02T17:15:37.785+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.checkStock]: PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED
2026-01-02T17:15:37.786+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(545209878<open>)] for JPA transaction
2026-01-02T17:15:37.797+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@2eb8c469]
2026-01-02T17:15:37.798+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(545209878<open>)] for JPA transaction
2026-01-02T17:15:37.798+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction B: Read stock as 5
2026-01-02T17:15:37.803+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T17:15:37.803+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(545209878<open>)]
2026-01-02T17:15:37.808+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
Stock read by Transaction B: 5
Transaction A: Rolling back the update
2026-01-02T17:15:40.831+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Transactional code has requested rollback
2026-01-02T17:15:40.832+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction rollback
2026-01-02T17:15:40.832+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Rolling back JPA transaction on EntityManager [SessionImpl(1891605271<open>)]
2026-01-02T17:15:40.840+05:30 DEBUG 36084 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction



mysql> select * from product;
+----+---------------------+--------+----------------+
| id | name                | price  | stock_quantity |
+----+---------------------+--------+----------------+
|  1 | Laptop              |  25000 |             50 |
|  2 | SmartPhone          |   5000 |              5 |
|  3 | Wireless Mouse      |  29.99 |            174 |
|  4 | Mechanical Keyboard |  89.99 |            150 |
|  5 | USB-C Hub           |  49.99 |            300 |
|  6 | External SSD 1TB    | 119.99 |             80 |
|  7 | Monitor 27-inch     | 299.99 |             40 |
|  8 | Webcam HD           |  79.99 |            120 |
|  9 | Bluetooth Speaker   |  59.99 |            180 |
+----+---------------------+--------+----------------+
9 rows in set (0.001 sec)










