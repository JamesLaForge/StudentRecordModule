
CREATE TABLE users (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR (255) NOT NULL,
  role VARCHAR(20) NOT NULL,
  PRIMARY KEY(id)
);


CREATE TABLE students (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  student_id INT NOT NULL,
  username VARCHAR(255) NOT NULL,
  teacher VARCHAR(255) NOT NULL,
  parent_id INT NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE parents (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  username VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE assessmentName (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  assessment_name VARCHAR(255) NOT NULL,
  date Date NOT NULL,
  type VARCHAR(20) NOT NULL,
  PRIMARY KEY (assessment_name)  
);

CREATE TABLE assessments (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  assessment_name VARCHAR(255) NOT NULL,
  date DATE NOT NULL,
  type VARCHAR(20) NOT NULL,
  userID int NOT NULL,
  score int NOT NULL,
  percentile INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (userID) REFERENCES users(id),
  FOREIGN KEY (assessment_name) REFERENCES assessmentName(assessment_name)
);




INSERT INTO users (username, password, firstName, lastName, role) VALUES 
('admin', 'password123', 'Jay', 'LaForge', 'Administrator'),
('jdoe1', 'password123', 'Jane', 'Doe', 'Teacher'),
('jsmith1', 'password123', 'John', 'Smith', 'Teacher'),
('sride1', 'password123', 'Sally', 'Ride', 'Student'),
('kride1', 'password123', 'Karen', 'Ride','Student'),
('jglenn1', 'password123','John', 'Glenn', 'Student'),
('rmcnair1', 'password123', 'Ronald', 'McNair', 'Student'),
('cride1', 'password123','Carol', 'Ride', 'Parent'),
('jglenn2', 'password123', 'John', 'Glenn Sr.', 'Parent'),
('pmcnair', 'password123', 'Pearl', 'McNair','Parent');

INSERT INTO parents (username) VALUES 
('cride1'),
('jglenn2'),
('pmcnair1');

INSERT INTO students (student_id, username, teacher, parent_id) VALUES 
(1, 'sride1', 'Jane Doe', 1),
(2, 'kride1', 'John Smith', 1),  
(3, 'jglenn1', 'Jane Doe', 2),
(4, 'rmcnair1', 'John Smith', 3);


INSERT INTO assessmentName (assessment_name, date, type) VALUES
('Assessment 1', '2023-08-06', 'Local'),
('Assessment 2', '2023-08-07', 'National'),
('Assessment 3', '2023-08-07', 'State'),
('Assessment 4', '2023-08-09', 'Local'),
('Assessment 5', '2023-08-09', 'National'),
('Assessment 6', '2023-08-11', 'State'),
('Assessment 7', '2023-08-12', 'Local'),
('Assessment 8', '2023-08-13', 'National'),
('Assessment 9', '2023-08-14', 'State'),
('Assessment 10', '2023-08-15', 'Local');



INSERT INTO assessments (assessment_name, date, type, userID, score, percentile) VALUES 
('Assessment 1', '2023-08-06', 'Local', 4, 85, NULL),
('Assessment 2', '2023-08-07', 'National', 4, 88, 92),
('Assessment 3', '2023-08-07', 'State', 4, 90, 94),
('Assessment 4', '2023-08-09', 'Local', 4, 94, NULL),
('Assessment 5', '2023-08-09', 'National', 4, 85, 88),
('Assessment 6', '2023-08-11', 'State', 4, 91, 93),
('Assessment 7', '2023-08-12', 'Local', 4, 92, NULL),
('Assessment 8', '2023-08-13', 'National', 4, 87, 89),
('Assessment 9', '2023-08-14', 'State', 4, 86, 91),
('Assessment 10', '2023-08-15', 'Local', 4, 89, NULL),
('Assessment 1', '2023-08-06', 'State', 5, 84, 87),
('Assessment 2', '2023-08-07', 'Local', 5, 85, NULL),
('Assessment 3', '2023-08-07', 'National', 5, 87, 91),
('Assessment 4', '2023-08-09', 'State', 5, 88, 91),
('Assessment 5', '2023-08-09', 'Local', 5, 86, NULL),
('Assessment 6', '2023-08-11', 'National', 5, 91, 94),
('Assessment 7', '2023-08-12', 'State', 5, 92, 95),
('Assessment 8', '2023-08-13', 'Local', 5, 89, NULL),
('Assessment 9', '2023-08-14', 'National', 5, 85, 88),
('Assessment 10', '2023-08-15', 'State', 5, 90, 93),
('Assessment 1', '2023-08-06', 'National', 6, 83, 86),
('Assessment 2', '2023-08-07', 'State', 6, 87, 90),
('Assessment 3', '2023-08-07', 'Local', 6, 86, NULL),
('Assessment 4', '2023-08-09', 'National', 6, 88, 91),
('Assessment 5', '2023-08-09', 'State', 6, 85, 88),
('Assessment 6', '2023-08-11', 'Local', 6, 91, NULL),
('Assessment 7', '2023-08-12', 'National', 6, 93, 96),
('Assessment 8', '2023-08-13', 'State', 6, 88, 91),
('Assessment 9', '2023-08-14', 'Local', 6, 89, NULL),
('Assessment 10', '2023-08-15', 'National', 6, 91, 94),
('Assessment 1', '2023-08-06', 'Local', 7, 84, NULL),
('Assessment 2', '2023-08-07', 'National', 7, 86, 89),
('Assessment 3', '2023-08-07', 'State', 7, 88, 91),
('Assessment 4', '2023-08-09', 'Local', 7, 90, NULL),
('Assessment 5', '2023-08-09', 'National', 7, 89, 92),
('Assessment 6', '2023-08-11', 'State', 7, 91, 94),
('Assessment 7', '2023-08-12', 'Local', 7, 92, NULL),
('Assessment 8', '2023-08-13', 'National', 7, 90, 93),
('Assessment 9', '2023-08-14', 'State', 7, 88, 91),
('Assessment 10', '2023-08-15', 'Local', 7, 94, NULL);





