CREATE TABLE users (
   id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name       VARCHAR(20) NOT NULL,
   login      VARCHAR(20) NOT NULL,
   email      VARCHAR(50) NOT NULL,
   photoId    INT,
   createDate DATE,
);