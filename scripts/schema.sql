
CREATE SCHEMA course;

CREATE TABLE course.courseCatalog (
	courseName text PRIMARY KEY
	-- fill in other information
);

CREATE SCHEMA users;

CREATE TABLE users.users (
	id uuid PRIMARY KEY,
	firstName text NOT NULL,
	lastName text NOT NULL,
	email text NOT NULL UNIQUE,
	year integer NOT NULL,
	program text,
	faculty text
);

CREATE TABLE users.roles (
	id uuid PRIMARY KEY,
	course text REFERENCES course.courseCatalog (courseName)
	--other information
);

