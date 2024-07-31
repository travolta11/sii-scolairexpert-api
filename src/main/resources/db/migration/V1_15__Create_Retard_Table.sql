CREATE TABLE retard(
    retard_id INT PRIMARY KEY,
    student_id INT,
    date DATE NOT NULL,
    reason VARCHAR(255),
    session_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (session_id) REFERENCES session(id)
);
