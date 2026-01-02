

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

-- new table created
CREATE TABLE order_processing_schema.auditlog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    action VARCHAR(255),
    timestamp DATETIME NOT NULL
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


