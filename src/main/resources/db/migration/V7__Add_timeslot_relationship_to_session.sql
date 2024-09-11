ALTER TABLE session
ADD COLUMN timeslot_id INT NOT NULL,
ADD CONSTRAINT fk_session_timeslot FOREIGN KEY (timeslot_id) REFERENCES timeslot(id);