 DELETE FROM espace;

ALTER TABLE espace AUTO_INCREMENT=1;

INSERT INTO espace(espace_name)
values ("espace 1"),
       ("espace 2"),
       ("espace 3");


/*
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



 DELETE FROM timeslot;
 ALTER TABLE timeslot AUTO_INCREMENT=1;

-- Inserting into Timeslot for One Week
 INSERT INTO timeslot (id, day_of_week, start_time, end_time) VALUES
-- Monday (Day 1)
(1, 1, '08:00:00', '09:00:00'),
(2, 1, '09:00:00', '10:00:00'),
(3, 1, '10:00:00', '11:00:00'),
(4, 1, '11:00:00', '12:00:00'),
-- Tuesday (Day 2)
(5, 2, '08:00:00', '09:00:00'),
(6, 2, '09:00:00', '10:00:00'),
(7, 2, '10:00:00', '11:00:00'),
(8, 2, '11:00:00', '12:00:00'),
-- Wednesday (Day 3)
(9, 3, '08:00:00', '09:00:00'),
(10, 3, '09:00:00', '10:00:00'),
(11, 3, '10:00:00', '11:00:00'),
(12, 3, '11:00:00', '12:00:00'),
-- Thursday (Day 4)
(13, 4, '08:00:00', '09:00:00'),
(14, 4, '09:00:00', '10:00:00'),
(15, 4, '10:00:00', '11:00:00'),
(16, 4, '11:00:00', '12:00:00'),
-- Friday (Day 5)
(17, 5, '08:00:00', '09:00:00'),
(18, 5, '09:00:00', '10:00:00'),
(19, 5, '10:00:00', '11:00:00'),
(20, 5, '11:00:00', '12:00:00');



-- Inserting into Session for One Week
 INSERT INTO session (id, class_id, subject_id, room_id, timeslot_id, teacher_id) VALUES
-- Class 1 (Class A)
(1, 1, 1, 1, 1, 1),  -- Monday
(2, 1, 2, 2, 5, 2),  -- Tuesday
(3, 1, 3, 3, 9, 3),  -- Wednesday
(4, 1, 4, 4, 13, 4), -- Thursday
(5, 1, 5, 5, 17, 5), -- Friday

-- Class 2 (Class B)
(6, 2, 1, 1, 2, 1),  -- Monday
(7, 2, 2, 2, 6, 2),  -- Tuesday
(8, 2, 3, 3, 10, 3), -- Wednesday
(9, 2, 4, 4, 14, 4), -- Thursday
(10, 2, 5, 5, 18, 5), -- Friday

-- Class 3 (Class C)
(11, 3, 1, 1, 3, 1),  -- Monday
(12, 3, 2, 2, 7, 2),  -- Tuesday
(13, 3, 3, 3, 11, 3), -- Wednesday
(14, 3, 4, 4, 15, 4), -- Thursday
(15, 3, 5, 5, 19, 5), -- Friday

-- Class 4 (Class D)
(16, 4, 1, 1, 4, 1),  -- Monday
(17, 4, 2, 2, 8, 2),  -- Tuesday
(18, 4, 3, 3, 12, 3), -- Wednesday
(19, 4, 4, 4, 16, 4), -- Thursday
(20, 4, 5, 5, 20, 5), -- Friday

-- Class 5 (Class E)
(21, 5, 1, 1, 5, 1),  -- Monday
(22, 5, 2, 2, 9, 2),  -- Tuesday
(23, 5, 3, 3, 10, 3), -- Wednesday
(24, 5, 4, 4, 11, 4), -- Thursday
(25, 5, 5, 5, 12, 5); -- Friday




-- parent data


 INSERT INTO parent (first_name, last_name, email, phone, cin, user_id) VALUES
                                                                            ('Ahmed', 'El Fassi', 'ahmed.elfassi@example.com', '+212612345678', 'AB123456', 1),
                                                                            ('Fatima', 'Bennani', 'fatima.bennani@example.com', '+212622345678', 'AC654321', 1),
                                                                            ('Mohamed', 'Bouazza', 'mohamed.bouazza@example.com', '+212633456789', 'AE789456', 1),
                                                                            ('Youssef', 'Amrani', 'youssef.amrani@example.com', '+212644567890', 'BC147258', 1),
                                                                            ('Leila', 'Oukili', 'leila.oukili@example.com', '+212655678901', 'BD369852', 1),
                                                                            ('Karima', 'El Idrissi', 'karima.elidrissi@example.com', '+212666789012', 'BF987654', 1),
                                                                            ('Hassan', 'Tazi', 'hassan.tazi@example.com', '+212677890123', 'BG456789', 1),
                                                                            ('Nadia', 'Ben Slimane', 'nadia.benslimane@example.com', '+212688901234', 'BH852963', 1),
                                                                            ('Abdellah', 'Kabbaj', 'abdellah.kabbaj@example.com', '+212699012345', 'BI753951', 1),
                                                                            ('Rachid', 'El Mansouri', 'rachid.elmansouri@example.com', '+212610123456', 'BJ159357', 1);
--*/

/* etudiant



 INSERT INTO student (first_name, last_name, email, phone_number, address, zip_code, gender, level, date_of_birth, parent_id, class_id) VALUES
                                                                                                                                            ('Aya', 'El Fassi', 'aya.elfassi@example.com', '+212612345111', '12 Rue Mohamed V, Casablanca', '20000', 'Female', 'Primary 4', '2012-03-15', 1, 1),
                                                                                                                                            ('Sami', 'Bennani', 'sami.bennani@example.com', '+212622345222', '8 Avenue Hassan II, Rabat', '10000', 'Male', 'Primary 5', '2011-06-10', 2, 1),
                                                                                                                                            ('Imane', 'Bouazza', 'imane.bouazza@example.com', '+212633456333', '45 Boulevard Zerktouni, Marrakech', '40000', 'Female', 'Secondary 1', '2010-09-22', 3, 1),
                                                                                                                                            ('Zakaria', 'Amrani', 'zakaria.amrani@example.com', '+212644567444', '22 Rue des Oiseaux, Fes', '30000', 'Male', 'Primary 3', '2013-01-29', 4, 1),
                                                                                                                                            ('Salma', 'Oukili', 'salma.oukili@example.com', '+212655678555', '33 Quartier Souissi, Rabat', '10000', 'Female', 'Secondary 2', '2009-04-05', 5, 1),
                                                                                                                                            ('Omar', 'El Idrissi', 'omar.elidrissi@example.com', '+212666789666', '99 Rue des Fleurs, Casablanca', '20000', 'Male', 'Secondary 3', '2008-08-18', 6, 1),
                                                                                                                                            ('Nada', 'Tazi', 'nada.tazi@example.com', '+212677890777', '5 Rue des Palmiers, Agadir', '80000', 'Female', 'Primary 6', '2011-12-11', 7, 1),
                                                                                                                                            ('Younes', 'Ben Slimane', 'younes.benslimane@example.com', '+212688901888', '17 Avenue des Far, Tangier', '90000', 'Male', 'Primary 2', '2014-02-20', 8, 1),
                                                                                                                                            ('Hiba', 'Kabbaj', 'hiba.kabbaj@example.com', '+212699012999', '28 Rue des Cedres, Oujda', '60000', 'Female', 'Primary 1', '2015-07-09', 9, 1),
                                                                                                                                            ('Khalid', 'El Mansouri', 'khalid.elmansouri@example.com', '+212610123101', '16 Rue Al Massira, Tetouan', '93000', 'Male', 'Secondary 1', '2010-05-17', 10, 1),
                                                                                                                                            ('Sara', 'El Fassi', 'sara.elfassi@example.com', '+212612345202', '23 Rue Ibn Battuta, Casablanca', '20000', 'Female', 'Primary 4', '2012-09-25', 1, 1),
                                                                                                                                            ('Mehdi', 'Bennani', 'mehdi.bennani@example.com', '+212622345303', '47 Avenue Moulay Youssef, Rabat', '10000', 'Male', 'Primary 5', '2011-03-14', 2, 1),
                                                                                                                                            ('Lina', 'Bouazza', 'lina.bouazza@example.com', '+212633456404', '32 Boulevard Ghandi, Marrakech', '40000', 'Female', 'Secondary 2', '2009-06-21', 3, 1),
                                                                                                                                            ('Rayan', 'Amrani', 'rayan.amrani@example.com', '+212644567505', '10 Rue des Merles, Fes', '30000', 'Male', 'Primary 3', '2013-10-30', 4, 1),
                                                                                                                                            ('Aya', 'Oukili', 'aya.oukili@example.com', '+212655678606', '11 Quartier Hay Riad, Rabat', '10000', 'Female', 'Secondary 3', '2008-07-11', 5, 1),
                                                                                                                                            ('Youssef', 'El Idrissi', 'youssef.elidrissi@example.com', '+212666789707', '90 Rue de la Liberté, Casablanca', '20000', 'Male', 'Secondary 1', '2010-11-28', 6, 1),
                                                                                                                                            ('Nour', 'Tazi', 'nour.tazi@example.com', '+212677890808', '9 Rue de Marrakech, Agadir', '80000', 'Female', 'Primary 6', '2011-01-12', 7, 1),
                                                                                                                                            ('Ismail', 'Ben Slimane', 'ismail.benslimane@example.com', '+212688901909', '20 Boulevard Mohamed Zerktouni, Tangier', '90000', 'Male', 'Primary 2', '2014-05-04', 8, 1),
                                                                                                                                            ('Manal', 'Kabbaj', 'manal.kabbaj@example.com', '+212699012010', '45 Rue de l’Océan, Oujda', '60000', 'Female', 'Primary 1', '2015-03-23', 9, 1),
                                                                                                                                            ('Hamza', 'El Mansouri', 'hamza.elmansouri@example.com', '+212610123111', '60 Rue du Golf, Tetouan', '93000', 'Male', 'Secondary 2', '2009-09-06', 10, 1);
*/

/* materiel



 INSERT INTO materiel (code, type, status) VALUES
                                               ('MAT001', 'Projector', 'Available'),
                                               ('MAT002', 'Whiteboard', 'Available'),
                                               ('MAT003', 'Computer', 'Available'),
                                               ('MAT004', 'Printer', 'Available'),
                                               ('MAT005', 'Textbook', 'Available'),
                                               ('MAT006', 'Laboratory Equipment', 'Available'),
                                               ('MAT007', 'Audio/Visual Equipment', 'Available'),
                                               ('MAT008', 'Library Book', 'Available'),
                                               ('MAT009', 'Whiteboard', 'Available'),
                                               ('MAT010', 'Projector', 'Available');


 */

/* staff
INSERT INTO staff (first_name, last_name, email, address, phone, cin, birth_date, start_date, gender, position, department) VALUES
('Samir', 'El Idrissi', 'samir.elidrissi@example.com', '12 Rue Hassan II, Rabat', '+212612345678', 'AB123456', '1980-05-15', '2010-09-01', 'Male', 'TEACHER', 'SCIENCE'),
('Nadia', 'Bouzid', 'nadia.bouzid@example.com', '23 Avenue Moulay Ismail, Casablanca', '+212622345678', 'AC654321', '1985-11-20', '2015-01-10', 'Female', 'ASSISTANT', 'HR'),
('Khalid', 'Tazi', 'khalid.tazi@example.com', '45 Boulevard Zerktouni, Marrakech', '+212633456789', 'AE789456', '1975-07-22', '2005-03-12', 'Male', 'CLEANER', 'FINANCE'),
('Fatima', 'Amrani', 'fatima.amrani@example.com', '16 Rue Ibn Khaldoun, Fes', '+212644567890', 'BC147258', '1990-03-18', '2018-06-25', 'Female', 'TEACHER', 'PHYSICS'),
('Youssef', 'Benjelloun', 'youssef.benjelloun@example.com', '10 Rue des Roses, Tangier', '+212655678901', 'BD369852', '1988-12-10', '2013-09-05', 'Male', 'SECURITY_OFFICER', 'IT'),
('Leila', 'Fassi', 'leila.fassi@example.com', '5 Rue des Palmiers, Agadir', '+212666789012', 'BF987654', '1982-04-01', '2010-10-15', 'Female', 'TEACHER', 'SCIENCE'),
('Ahmed', 'El Kabbaj', 'ahmed.elkabbaj@example.com', '17 Avenue Mohammed V, Rabat', '+212677890123', 'BG456789', '1978-06-30', '2008-05-23', 'Male', 'CLEANER', 'PHYSICS'),
('Sara', 'Bennani', 'sara.bennani@example.com', '22 Boulevard Al Massira, Casablanca', '+212688901234', 'BH852963', '1987-08-17', '2014-11-01', 'Female', 'ASSISTANT', 'HR'),
('Rachid', 'El Fassi', 'rachid.elfassi@example.com', '30 Rue des Cedres, Marrakech', '+212699012345', 'BI753951', '1992-02-24', '2019-02-10', 'Male', 'TEACHER', 'IT'),
('Khadija', 'El Mansouri', 'khadija.elmansouri@example.com', '18 Avenue des Far, Tangier', '+212610123456', 'BJ159357', '1983-09-14', '2012-08-12', 'Female', 'SECURITY_OFFICER', 'FINANCE');

 */

