CREATE TABLE payment (
    payment_id INT PRIMARY KEY,
    student_id INT,
    fee_id INT,
    payment_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (fee_id) REFERENCES fees(fee_id)
);
