DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

CREATE TABLE author(
name VARCHAR(30) NOT NULL,
age SMALLINT,
id INTEGER PRIMARY KEY
);

CREATE TABLE book(
title VARCHAR(30) NOT NULL,
author_id INTEGER REFERENCES author(id),
isbn VARCHAR(20) PRIMARY KEY
);