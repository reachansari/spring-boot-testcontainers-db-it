
CREATE TABLE Customer (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     firstName VARCHAR(255),
     lastName VARCHAR(255),
     dob VARCHAR(255),
     gender VARCHAR(255)
);

INSERT INTO Customer (id, firstName, lastName, dob, gender) VALUES (1, 'Jack', 'jones', '1990-01-11', 'male');
INSERT INTO Customer (id, firstName, lastName, dob, gender) VALUES (2, 'Olivia', 'mia', '2001-10-19', 'female');