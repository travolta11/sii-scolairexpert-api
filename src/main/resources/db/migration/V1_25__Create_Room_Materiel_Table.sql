CREATE TABLE room_materiel (
                               room_id INT NOT NULL,
                               materiel_id INT NOT NULL,
                               PRIMARY KEY (room_id, materiel_id),
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_room_materiel_room FOREIGN KEY (room_id) REFERENCES room(id),
                               CONSTRAINT fk_room_materiel_materiel FOREIGN KEY (materiel_id) REFERENCES materiel(id)
);
