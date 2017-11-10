
--users
-- INSERT INTO users.users (id, first_name, last_name, email, year, program, faculty, user_type)
--   VALUES (uuid_generate_v4(), 'Jacob', 'Moore', 'jaemoore@uwaterloo.ca', 3, 'Software Engineering', 'VP ADMIN', 'TUTOR');
--
-- INSERT INTO users.users (id, first_name, last_name, email, year, program, faculty, user_type)
--   VALUES (uuid_generate_v4(), 'Josh', 'Carnide', 'jcarnide@uwaterloo.ca', 1, 'Computer Science', 'Math', 'TUTOR');
--
-- INSERT INTO users.users (id, first_name, last_name, email, year, program, faculty, user_type)
--   VALUES (uuid_generate_v4(), 'Jitin', 'Dodd', 'j2dodd@uwaterloo.ca', 2, 'Mechanical Engineering', 'Engineering', 'STANDARD');
--
-- INSERT INTO users.users (id, first_name, last_name, email, year, program, faculty, user_type)
--   VALUES (uuid_generate_v4(), 'Charles', 'Bai', 'cbai@uwaterloo.ca', 4, 'Software Engineering', 'VP ADMIN', 'TUTOR');

--course catalog

INSERT INTO course.course_catalog (id, code, subject, catalog_number, course_name, description, academic_level, calendar_year, url)
  VALUES (1, 'CS123', 'CS', 123, 'Computer Science', 'Description', 'undergraduate', 2017, 'url.com');

INSERT INTO course.course_catalog (id, code, subject, catalog_number, course_name, description, academic_level, calendar_year, url)
  VALUES (2, 'AMATH123', 'AMATH', 123, 'Applied Math', 'Description', 'graduate', 2017, 'url.com');

-- tutors

-- INSERT INTO users.tutors (uid, course_id)
--   VALUES ((SELECT id FROM users.users WHERE first_name = 'Jacob'), 1);
--
-- INSERT INTO users.tutors (uid, course_id)
-- VALUES ((SELECT id FROM users.users WHERE first_name = 'Jacob'), 2);
--
-- INSERT INTO users.tutors (uid, course_id)
--   VALUES ((SELECT id FROM users.users WHERE first_name = 'Josh'), 1);
--
-- INSERT INTO users.tutors (uid, course_id)
--   VALUES ((SELECT id FROM users.users WHERE first_name = 'Charles'), 2);
