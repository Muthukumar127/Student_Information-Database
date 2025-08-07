DROP DATABASE muthustudents_db;
CREATE DATABASE IF NOT EXISTS muthustudents_db;
USE muthustudents_db;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50)
);
select * from students;
