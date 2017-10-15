CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP SCHEMA IF EXISTS course CASCADE;
CREATE SCHEMA course;

CREATE TABLE course.course_catalog (
  id integer PRIMARY KEY,
  code text NOT NULL UNIQUE,
  subject text NOT NULL,
  catalog_number integer NOT NULL,
  course_name text,
  description text,
  academic_level text,
  calendar_year integer,
  url text
);

DROP SCHEMA IF EXISTS users CASCADE;
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

CREATE TABLE users.tutors (
	id SERIAL PRIMARY KEY,
	uid uuid REFERENCES users.users (id),
	course_id integer REFERENCES course.course_catalog (id),
  UNIQUE (uid, course_id)
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
	course_id integer REFERENCES course.course_catalog (id)
);

