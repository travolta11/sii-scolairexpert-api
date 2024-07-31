CREATE TABLE class_subject (
    class_id INT NOT NULL,
    subject_id INT NOT NULL,
    PRIMARY KEY (class_id, subject_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_class_subject_class FOREIGN KEY (class_id) REFERENCES class(id),
    CONSTRAINT fk_class_subject_subject FOREIGN KEY (subject_id) REFERENCES subject(id)
);