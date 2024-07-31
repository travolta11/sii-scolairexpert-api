CREATE TABLE grade (
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    grade DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (student_id, subject_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_student_subject_grade_student FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT fk_student_subject_grade_subject FOREIGN KEY (subject_id) REFERENCES subject(id)
);