package com.grasp.dao;

import com.grasp.model.CourseCatalog;
import com.grasp.model.Tutor;
import com.grasp.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
public class UserDaoTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseCatalogDao courseCatalogDao;

    @Autowired
    private TutorDao tutorDao;

    @Test
    public void test() throws Exception {
        User user = new User("Jacob2", "Moore", "some Email..", 4, "se", "vpadmin");

        userDao.save(user);

        user = userDao.findUserByFirstName("Jacob2");

        CourseCatalog courseCatalog = new CourseCatalog();
        courseCatalog.setId(1);
        courseCatalog.setCatalogNumber(123);
        courseCatalog.setCode("MATH 123");
        courseCatalog.setSubject("MATH");

        courseCatalogDao.save(courseCatalog);

        courseCatalogDao.findByCode("MATH 123");

        List<Tutor> tutors = new ArrayList<>();

        Tutor tutor = new Tutor(user.getId(), courseCatalog);

        tutors.add(tutor);

        user.setTutors(tutors);

        userDao.save(user);

        System.out.println(userDao.findUserById(user.getId()).toString());
    }
}