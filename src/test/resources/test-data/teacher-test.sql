DELETE FROM teacher;
ALTER TABLE teacher AUTO_INCREMENT=1;

-- Insertion des données de test
INSERT INTO teacher (full_name, email, phone)
VALUES
    ('Alice',  'alice.smith@example.com',   '12345678'),
    ('Bob', 'bob.johnson@example.com',  '87654321'),
    ('Charlie',  'charlie.brown@example.com',   '11223344');