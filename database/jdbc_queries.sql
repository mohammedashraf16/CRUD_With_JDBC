DROP DATABASE IF EXISTS jdbc_db;
create database jdbc_db;
use jdbc_db;
create table employee(
    id int(5) primary key auto_increment,
    name varchar(50),
    gender boolean,
    birthdate date,
    salary real
)