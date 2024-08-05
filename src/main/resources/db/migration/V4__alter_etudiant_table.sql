ALTER TABLE etudiants ADD COLUMN parent_id BIGINT;

ALTER TABLE etudiants ADD CONSTRAINT fk_parent FOREIGN KEY (parent_id) REFERENCES parent(id);
