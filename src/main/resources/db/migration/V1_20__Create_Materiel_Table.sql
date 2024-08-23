CREATE TABLE materiel (
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      code VARCHAR(100) NOT NULL,
                      Type VARCHAR(100),
                      Status VARCHAR(100),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);