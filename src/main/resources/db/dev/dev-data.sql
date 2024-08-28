DELETE FROM espace;
ALTER TABLE espace AUTO_INCREMENT=1;

INSERT INTO espace(espace_name)
values ("espace 1"),
       ("espace 2"),
       ("espace 3");



  INSERT INTO users(username,password,email,role)
 values ("admin","issam","admin@gmail.com","admin"),
       ("administration","issam","administration@gmail.com","administration"),
       ("accounting","issam","accounting@gmail.com","accounting");

DELETE FROM class;
ALTER TABLE class AUTO_INCREMENT=1;
-- Inserting into Class
INSERT INTO class (id, class_name, number_of_students) VALUES 
(1, 'Class A', 30),
(2, 'Class B', 25),
(3, 'Class C', 28),
(4, 'Class D', 32),
(5, 'Class E', 29);


DELETE FROM teacher;
ALTER TABLE teacher AUTO_INCREMENT=1;
-- Inserting into Teacher
INSERT INTO teacher (id, full_name, email, phone) VALUES 
(1, 'John Doe', 'john.doe@example.com', '1234567890'),
(2, 'Jane Smith', 'jane.smith@example.com', '0987654321'),
(3, 'Emily Johnson', 'emily.johnson@example.com', '2345678901'),
(4, 'Michael Brown', 'michael.brown@example.com', '3456789012'),
(5, 'Sarah Davis', 'sarah.davis@example.com', '4567890123');

DELETE FROM subject;
ALTER TABLE subject AUTO_INCREMENT=1;
-- Inserting into Subject
INSERT INTO subject (id, name, coefficient, teacher_id) VALUES 
(1, 'Mathematics', 4, 1),
(2, 'Physics', 3, 2),
(3, 'Chemistry', 3, 3),
(4, 'Biology', 3, 4),
(5, 'History', 2, 5);


DELETE FROM room;
ALTER TABLE room AUTO_INCREMENT=1;
-- Inserting into Room
INSERT INTO room (id, name, capacity) VALUES 
(1, 'Room 101', 30),
(2, 'Room 102', 25),
(3, 'Room 103', 28),
(4, 'Room 104', 32),
(5, 'Room 105', 29);



-- Inserting into Timeslot
INSERT INTO timeslot (id, day_of_week, start_time, end_time) VALUES 
(1, 1, '08:00:00', '09:00:00'),
(2, 1, '09:00:00', '10:00:00'),
(3, 1, '10:00:00', '11:00:00'),
(4, 1, '11:00:00', '12:00:00'),
(5, 2, '08:00:00', '09:00:00'),
(6, 2, '09:00:00', '10:00:00'),
(7, 2, '10:00:00', '11:00:00'),
(8, 2, '11:00:00', '12:00:00'),
(9, 3, '08:00:00', '09:00:00'),
(10, 3, '09:00:00', '10:00:00');

-- Inserting into Session
INSERT INTO session (id, class_id, subject_id, room_id, timeslot_id, teacher_id) VALUES 
-- Class 1
(1, 1, 1, 1, 1, 1),  -- Class A, Subject 1, Room 1, Timeslot 1, Teacher 1
(2, 1, 2, 2, 2, 2),  -- Class A, Subject 2, Room 2, Timeslot 2, Teacher 2
(3, 1, 3, 3, 3, 3),  -- Class A, Subject 3, Room 3, Timeslot 3, Teacher 3
(4, 1, 4, 4, 4, 4),  -- Class A, Subject 4, Room 4, Timeslot 4, Teacher 4
(5, 1, 5, 5, 5, 5),  -- Class A, Subject 5, Room 5, Timeslot 5, Teacher 5

-- Class 2
(6, 2, 1, 1, 2, 1),  -- Class B, Subject 1, Room 1, Timeslot 2, Teacher 1
(7, 2, 2, 2, 3, 2),  -- Class B, Subject 2, Room 2, Timeslot 3, Teacher 2
(8, 2, 3, 3, 4, 3),  -- Class B, Subject 3, Room 3, Timeslot 4, Teacher 3
(9, 2, 4, 4, 5, 4),  -- Class B, Subject 4, Room 4, Timeslot 5, Teacher 4
(10, 2, 5, 5, 1, 5), -- Class B, Subject 5, Room 5, Timeslot 1, Teacher 5

-- Class 3
(11, 3, 1, 1, 3, 1),  -- Class C, Subject 1, Room 1, Timeslot 3, Teacher 1
(12, 3, 2, 2, 4, 2),  -- Class C, Subject 2, Room 2, Timeslot 4, Teacher 2
(13, 3, 3, 3, 5, 3),  -- Class C, Subject 3, Room 3, Timeslot 5, Teacher 3
(14, 3, 4, 4, 1, 4),  -- Class C, Subject 4, Room 4, Timeslot 1, Teacher 4
(15, 3, 5, 5, 2, 5),  -- Class C, Subject 5, Room 5, Timeslot 2, Teacher 5

-- Class 4
(16, 4, 1, 1, 4, 1),  -- Class D, Subject 1, Room 1, Timeslot 4, Teacher 1
(17, 4, 2, 2, 5, 2),  -- Class D, Subject 2, Room 2, Timeslot 5, Teacher 2
(18, 4, 3, 3, 1, 3),  -- Class D, Subject 3, Room 3, Timeslot 1, Teacher 3
(19, 4, 4, 4, 2, 4),  -- Class D, Subject 4, Room 4, Timeslot 2, Teacher 4
(20, 4, 5, 5, 3, 5),  -- Class D, Subject 5, Room 5, Timeslot 3, Teacher 5

-- Class 5
(21, 5, 1, 1, 5, 1),  -- Class E, Subject 1, Room 1, Timeslot 5, Teacher 1
(22, 5, 2, 2, 1, 2),  -- Class E, Subject 2, Room 2, Timeslot 1, Teacher 2
(23, 5, 3, 3, 2, 3),  -- Class E, Subject 3, Room 3, Timeslot 2, Teacher 3
(24, 5, 4, 4, 3, 4),  -- Class E, Subject 4, Room 4, Timeslot 3, Teacher 4
(25, 5, 5, 5, 4, 5);  -- Class E, Subject 5, Room 5, Timeslot 4, Teacher 5


