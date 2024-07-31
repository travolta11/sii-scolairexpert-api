CREATE TABLE document (
    document_id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    issue_date DATE NOT NULL,
    type_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (type_id) REFERENCES documentTypes(type_id)

);
