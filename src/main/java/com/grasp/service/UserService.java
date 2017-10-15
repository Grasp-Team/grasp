package com.grasp.service;

import com.grasp.dao.UserDao;
import com.grasp.model.User;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getByName(@PathVariable("email") String email) {
        return userDao.findUserByEmail(email);
    }

    public User getById(@PathVariable("id") UUID uid) {
        return userDao.findUserById(uid);
    }

    public List<User> getAllUsers() {

        List<User> users = (List<User>) userDao.findAll();

        if(CollectionHelper.isEmpty(users)) {
            return new ArrayList<>();
        }

        return users;
    }

}
