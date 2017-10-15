package com.grasp.controller;

import com.grasp.model.User;
import com.grasp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/email/{email}")
    public User getByName(@PathVariable("email") String email) {
        return userService.getByName(email);
    }

    @RequestMapping("/id/{id}")
    public User getById(@PathVariable("id") UUID uid) {
        return userService.getById(uid);
    }

    @RequestMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
