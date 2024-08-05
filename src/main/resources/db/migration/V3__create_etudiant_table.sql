CREATE TABLE etudiants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    address VARCHAR(255),
    zip_code VARCHAR(10),
    gender VARCHAR(10),
    level VARCHAR(20),
    classe VARCHAR(20)
);
