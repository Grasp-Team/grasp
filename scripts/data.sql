
--users
INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Jacob', 'Moore', 'jaemoore@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 3, 'Software Engineering', 'VP ADMIN', 'TUTOR', 'STANDARD');

INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Josh', 'Carnide', 'jcarnide@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 1, 'Computer Science', 'Math', 'TUTOR', 'STANDARD');

INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Jitin', 'Dodd', 'j2dodd@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 2, 'Mechanical Engineering', 'Engineering', 'STANDARD', 'STANDARD');

INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Charles', 'Bai', 'cbai@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK' ,4, 'Software Engineering', 'VP ADMIN', 'TUTOR', 'STANDARD');

--course catalog

INSERT INTO course.course_catalog (code, subject, catalog_number, course_name, description, academic_level, calendar_year, url)
  VALUES ('CS123', 'CS', 123, 'Computer Science', 'Description', 'undergraduate', 2017, 'url.com');

INSERT INTO course.course_catalog (code, subject, catalog_number, course_name, description, academic_level, calendar_year, url)
  VALUES ('AMATH123', 'AMATH', 123, 'Applied Math', 'Description', 'graduate', 2017, 'url.com');

--tutors

INSERT INTO users.tutors (uid, course_code)
  VALUES ((SELECT id FROM users.users WHERE first_name = 'Jacob'), 'CS123');

INSERT INTO users.tutors (uid, course_code)
VALUES ((SELECT id FROM users.users WHERE first_name = 'Jacob'), 'AMATH123');

INSERT INTO users.tutors (uid, course_code)
  VALUES ((SELECT id FROM users.users WHERE first_name = 'Josh'), 'CS123');

INSERT INTO users.tutors (uid, course_code)
  VALUES ((SELECT id FROM users.users WHERE first_name = 'Charles'), 'AMATH123');
