CREATE TABLE IF NOT EXISTS student
(

     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
     hurigana VARCHAR(50),
     nickname VARCHAR(50) ,
     mailaddress VARCHAR(50) ,
     area VARCHAR(100),
     age INT,
     sex VARCHAR(10),
     remark VARCHAR(255),
     is_deleted boolean
);

CREATE TABLE IF NOT EXISTS studentscourses
(
     course_id INT AUTO_INCREMENT PRIMARY KEY,
     student_id INT NOT NULL,
     course_name VARCHAR(50),
     start_date  VARCHAR(50),
     end_date  VARCHAR(50)
);
