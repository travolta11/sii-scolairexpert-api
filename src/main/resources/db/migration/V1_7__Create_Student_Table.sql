CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    address VARCHAR(255),
    zip_code VARCHAR(10),
    gender VARCHAR(10),
    level VARCHAR(20),
    date_of_birth DATE,
    parent_id INT,
    class_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_student_parent
      FOREIGN KEY (parent_id) REFERENCES parent(id),
    CONSTRAINT fk_student_class 
      FOREIGN KEY (class_id) REFERENCES class(id)

);
