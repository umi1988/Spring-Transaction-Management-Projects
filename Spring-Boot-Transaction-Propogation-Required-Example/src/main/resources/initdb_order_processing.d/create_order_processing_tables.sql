

CREATE TABLE order_processing_schema.orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE order_processing_schema.product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    stock_quantity INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;





INSERT INTO order_processing_schema.product (name, price, stock_quantity) VALUES
('Laptop', 25000.0, 10),
('SmartPhone', 5000.0, 12),
('Wireless Mouse', 29.99, 200),
('Mechanical Keyboard', 89.99, 150),
('USB-C Hub', 49.99, 300),
('External SSD 1TB', 119.99, 80),
('Monitor 27-inch', 299.99, 40),
('Webcam HD', 79.99, 120),
('Bluetooth Speaker', 59.99, 180);


--INSERT INTO order_processing_schema.ORDERS (product_id, quantity, total_price) VALUES
--(1, 2, 50000.0),        -- 2 x Laptop
--(2, 5, 149.95),         -- 5 x Wireless Mouse
--(3, 1, 89.99),          -- 1 x Mechanical Keyboard
--(1, 1, 1299.99),        -- 1 x Laptop Pro
--(5, 3, 359.97),         -- 3 x External SSD
--(6, 2, 599.98),         -- 2 x Monitor
--(7, 4, 319.96),         -- 4 x Webcam
--(8, 2, 119.98);         -- 2 x Bluetooth Speaker