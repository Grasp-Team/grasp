package com.grasp.service;

import com.grasp.dao.UserDao;
import com.grasp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/user")
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/create")
    public void create() {
        User user = new User("Jacob", "Moore", "some Email", 4, "se", "vpadmin");
        userDao.save(user);
    }

    @RequestMapping("/name/{name}")
    public String getByName(@PathVariable("name") String name) {
        return userDao.findUserByFirstName(name).toString();
    }
}
