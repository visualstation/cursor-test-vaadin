CREATE TABLE IF NOT EXISTS customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(100),
    country VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO customer (first_name, last_name, email, phone, address, city, country) VALUES
('John', 'Doe', 'john.doe@example.com', '+1234567890', '123 Main St', 'New York', 'USA'),
('Jane', 'Smith', 'jane.smith@example.com', '+1234567891', '456 Oak Ave', 'Los Angeles', 'USA'),
('Bob', 'Johnson', 'bob.johnson@example.com', '+1234567892', '789 Pine Rd', 'Chicago', 'USA');
