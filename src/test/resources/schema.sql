CREATE TABLE IF NOT EXISTS students
(
 id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    hurigana VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    area VARCHAR(20) NOT NULL,
    years INT NOT NULL,
    gender VARCHAR(5) NOT NULL,
    remark VARCHAR(100),
    isDeleted TINYINT(1) NOT NULL,
    applicationStatus  VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS students_courses
(
course_id VARCHAR(100)  NOT NULL PRIMARY KEY,
    student_id INT ,
    courses VARCHAR(100) NOT NULL,
    start TIMESTAMP NOT NULL,
   endDate TIMESTAMP NULL,
   course_fee DECIMAL(7,0),
   payment_status CHAR(1),
       FOREIGN KEY (student_id) REFERENCES students(id)
);


