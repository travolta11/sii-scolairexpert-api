CREATE TABLE notification(
    notification_id INT PRIMARY KEY,
    parent_id INT,
    message TEXT NOT NULL,
    date_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES parent(id)
);
