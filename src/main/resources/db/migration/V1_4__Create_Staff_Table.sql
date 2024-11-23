CREATE TABLE staff (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    cin VARCHAR(20),
    birth_date DATE,
    start_date DATE,
    gender VARCHAR(10),
    position VARCHAR(50),
    department VARCHAR(50),
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT inheritance_between_user_and_staff FOREIGN KEY (user_id) REFERENCES users(id)
);
