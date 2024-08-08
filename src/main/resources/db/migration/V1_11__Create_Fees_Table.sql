CREATE TABLE fees (
    fee_id INT PRIMARY KEY,
    fee_type VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);