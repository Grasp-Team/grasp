package com.grasp.service;

import com.grasp.dao.UserDao;
import com.grasp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/email/{email}")
    public User getByName(@PathVariable("email") String email) {
        return userDao.findUserByFirstName(email);
    }

    @RequestMapping("/id/{id}")
    public User getById(@PathVariable("id") UUID uid) {
        return userDao.findUserById(uid);
    }

    @RequestMapping("/")
    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }

}
