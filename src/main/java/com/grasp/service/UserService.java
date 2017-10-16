package com.grasp.service;

import com.grasp.dao.UserDao;
import com.grasp.model.Tutor;
import com.grasp.model.User;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserDao userDao;
    private TutorService tutorService;

    @Autowired
    public UserService(UserDao userDao, TutorService tutorService) {
        this.userDao = userDao;
        this.tutorService = tutorService;
    }

    public User getByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public User getById(UUID uid) {
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
