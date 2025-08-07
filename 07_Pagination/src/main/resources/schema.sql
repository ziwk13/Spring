DROP DATABASE IF EXISTS db_pagination;
CREATE DATABASE IF NOT EXISTS db_pagination;
USE db_pagination;

DROP TABLE IF EXISTS tbl_user;
CREATE TABLE IF NOT EXISTS tbl_user (
	uid        INT,
	first_name VARCHAR(50),
	last_name  VARCHAR(50),
	email      VARCHAR(50),
	gender     VARCHAR(50),
	ip_address VARCHAR(20)
);