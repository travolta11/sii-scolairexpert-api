CREATE TABLE staff
(
    id bigint NOT NULL AUTO_INCREMENT,
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
    PRIMARY KEY (id)
)