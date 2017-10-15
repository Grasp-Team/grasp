
CREATE SCHEMA course;

CREATE TABLE course.courseCatalog (
	courseName text PRIMARY KEY
	-- fill in other information
);

CREATE SCHEMA users;

CREATE TABLE users.users (
	id uuid PRIMARY KEY,
	first_name text NOT NULL,
	last_name text NOT NULL,
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

CREATE TABLE users.relationships (
	id SERIAL PRIMARY KEY,
	tutor uuid REFERENCES users.users (id),
	student uuid REFERENCES users.users (id),
	status text CHECK (status='Accepted' OR status='Pending' OR status='Rejected')
);

CREATE TABLE course.course (
	id SERIAL PRIMARY KEY,
	uid uuid REFERENCES users.users (id),
	course text REFERENCES course.courseCatalog (courseName)
);

