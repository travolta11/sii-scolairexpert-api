CREATE TABLE `users` (
                         `id` INT(20) NOT NULL AUTO_INCREMENT,
                         `username` VARCHAR(50) NOT NULL,
                         `password` VARCHAR(250) NOT NULL,
                         `email` VARCHAR(50),
                         `role` VARCHAR(20) NOT NULL,
                         PRIMARY KEY (`id`)
);