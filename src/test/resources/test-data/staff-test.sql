-- Nettoyage de la table avant l'insertion des données de test
DELETE FROM staff;

ALTER TABLE staff AUTO_INCREMENT=1;

-- Insertion des données de test
INSERT INTO staff (first_name, last_name, email, address, phone, cin, birth_date, start_date, gender, position, department)
VALUES
    ('Alice', 'Smith', 'alice.smith@example.com', '123 Main St', '555-1234', '12345678', '1985-01-15', '2020-01-01', 'FEMALE', 'TEACHER', 'IT'),
    ('Bob', 'Johnson', 'bob.johnson@example.com', '456 Elm St', '555-5678', '87654321', '1990-02-20', '2021-02-01', 'MALE', 'CLEANER', 'IT'),
    ('Charlie', 'Brown', 'charlie.brown@example.com', '789 Maple St', '555-8765', '11223344', '1988-03-25', '2022-03-01', 'MALE', 'TEACHER', 'IT');