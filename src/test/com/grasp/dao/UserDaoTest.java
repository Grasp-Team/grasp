package com.grasp.dao;

import com.grasp.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-integration.properties")
public class UserDaoTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Autowired
    private UserDao userDao;

    @Test
    public void test() throws Exception {
        User user = new User("Jacob1", "Moore", "some Email..", 4, "se", "vpadmin");
        userDao.save(user);
        System.out.println((userDao.findUserByFirstName("Jacob1").toString()));
    }
}