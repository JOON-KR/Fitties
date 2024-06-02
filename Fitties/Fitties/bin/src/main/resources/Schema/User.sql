create database fitties;
use fitties;

CREATE TABLE user (
    id VARCHAR(50) NOT NULL PRIMARY key,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    name VARCHAR(50),
    nickname VARCHAR(50)
);

CREATE TABLE userProfile (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_Id VARCHAR(100),
    gender VARCHAR(10),
    image VARCHAR(300),
    reg_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    muscle DOUBLE,
    fat DOUBLE,
    bmi DOUBLE,
    fat_Per DOUBLE,
    weight DOUBLE
);

CREATE TABLE userrank (
    user_id VARCHAR(100) PRIMARY KEY,
    tier INT NOT NULL DEFAULT 0,
    exp INT NOT NULL DEFAULT 0,
    level INT NOT NULL DEFAULT 1,
    streak INT NOT NULL DEFAULT 0
);


