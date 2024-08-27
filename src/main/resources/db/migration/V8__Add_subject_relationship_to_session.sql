ALTER TABLE session
ADD COLUMN subject_id INT NOT NULL,
ADD CONSTRAINT fk_seance_subject
    FOREIGN KEY (subject_id) REFERENCES subject(id);
