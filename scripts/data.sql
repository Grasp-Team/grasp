
--users
INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Jacob', 'Moore', 'jaemoore@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 3, 'Software Engineering', 'VP ADMIN', 'TUTOR', 'STANDARD');

INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Josh', 'Carnide', 'jcarnide@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 1, 'Computer Science', 'Math', 'TUTOR', 'STANDARD');

INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Jitin', 'Dodd', 'j2dodd@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 2, 'Mechanical Engineering', 'Engineering', 'STANDARD', 'STANDARD');

INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
  VALUES (uuid_generate_v4(), 'Charles', 'Bai', 'cbai@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK' ,4, 'Software Engineering', 'VP ADMIN', 'TUTOR', 'STANDARD');

-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
--   VALUES (uuid_generate_v4(), 'Waterloo1', 'Waterloo1', 'waterloo1@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 3, 'Software Engineering', 'VP ADMIN', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
--   VALUES (uuid_generate_v4(), 'Waterloo2', 'Waterloo2', 'waterloo2@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 1, 'Computer Science', 'Math', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
--   VALUES (uuid_generate_v4(), 'Waterloo3', 'Waterloo3', 'waterloo3@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 2, 'Mechanical Engineering', 'Engineering', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
--   VALUES (uuid_generate_v4(), 'Waterloo4', 'Waterloo4', 'waterloo4@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK' ,4, 'Software Engineering', 'VP ADMIN', 'STANDARD', 'STANDARD');
--
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo5', 'Waterloo5', 'waterloo5@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 3, 'Software Engineering', 'VP ADMIN', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo6', 'Waterloo6', 'waterloo6@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 1, 'Computer Science', 'Math', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo7', 'Waterloo7', 'waterloo7@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 2, 'Mechanical Engineering', 'Engineering', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo8', 'Waterloo8', 'waterloo8@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK' ,4, 'Software Engineering', 'VP ADMIN', 'STANDARD', 'STANDARD');
--
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo9', 'Waterloo9', 'waterloo9@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 3, 'Software Engineering', 'VP ADMIN', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo10', 'Waterloo10', 'waterloo10@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 1, 'Computer Science', 'Math', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo11', 'Waterloo11', 'waterloo11@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK', 2, 'Mechanical Engineering', 'Engineering', 'STANDARD', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, password, year, program, faculty, user_type, user_role)
-- VALUES (uuid_generate_v4(), 'Waterloo12', 'Waterloo12', 'waterloo12@uwaterloo.ca', '$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK' ,4, 'Software Engineering', 'VP ADMIN', 'STANDARD', 'STANDARD');


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


