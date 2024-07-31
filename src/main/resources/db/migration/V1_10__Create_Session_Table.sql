CREATE TABLE session (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    teacher_id INT NOT NULL,
    class_id INT NOT NULL,
    room_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_seance_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    CONSTRAINT fk_seance_class FOREIGN KEY (class_id) REFERENCES class(id),
    CONSTRAINT fk_seance_room FOREIGN KEY (room_id) REFERENCES room(id)
);