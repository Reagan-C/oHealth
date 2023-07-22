
 CREATE TABLE IF NOT EXISTS `user` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `first_name` VARCHAR(255) NOT NULL,
        `last_name` VARCHAR(255) NOT NULL,
        `email_address` VARCHAR(255) NOT NULL,
        `phone_number` VARCHAR(255) NOT NULL,
        `gender` VARCHAR(255) NOT NULL,
        `address` VARCHAR(255) NOT NULL,
        `password` VARCHAR(255) NOT NULL,
        `date_of_birth` DATE NOT NULL,
        `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

         PRIMARY KEY (`id`)
 );

CREATE TABLE IF NOT EXISTS `appointment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `patient_id` BIGINT,
    `doctor_id` BIGINT,
    `appointment_date` DATE NOT NULL,

    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `chat_message` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `chat_room_id` BIGINT,
    `user_id` BIGINT,
    `content` VARCHAR(255),
    `sent_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `doctor` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT,

    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `manager` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT,

    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `medical_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `patient_id` BIGINT NOT NULL,
    `condition_name` VARCHAR(255),
    `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `patient` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT,

    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `qualification` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT,
    `cert_number` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `roles` (
     `id` BIGINT NOT NULL AUTO_INCREMENT,
     `code` VARCHAR(255) NOT NULL,
     `title` VARCHAR(255) NOT NULL,
     `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

      PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `vitals` (
     `id` BIGINT NOT NULL AUTO_INCREMENT,
     `patient_id` BIGINT,
     `temperature` VARCHAR(255) NOT NULL,
     `weight` VARCHAR(255) NOT NULL,
     `blood_pressure` VARCHAR(255) NOT NULL,
     `checked_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

     PRIMARY KEY (`id`)
 );

 CREATE TABLE IF NOT EXISTS `chat_rooms` (
         `id` BIGINT NOT NULL AUTO_INCREMENT,
         `doctor_id` BIGINT,
         `patient_id` BIGINT,
         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

          primary key (`id`)
 ) ;


