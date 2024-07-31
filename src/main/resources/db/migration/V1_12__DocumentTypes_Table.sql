CREATE TABLE documentTypes (
    type_id INT PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
