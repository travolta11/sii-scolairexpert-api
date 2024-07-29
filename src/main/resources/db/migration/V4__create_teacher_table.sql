CREATE TABLE teacher (
    teacher_id BIGINT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_no VARCHAR(100) NOT NULL,
    PRIMARY KEY (teacher_id)
);
