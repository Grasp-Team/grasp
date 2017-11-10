package com.grasp.service;

import com.grasp.dao.UserDao;
import com.grasp.model.User;
import com.grasp.security.model.APIAuthenticationToken;
import com.grasp.security.model.UserAuthenticationRequest;
import com.grasp.security.model.UserAuthenticationResponse;
import com.grasp.util.CollectionHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private UserDao userDao;
    private TutorService tutorService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, TutorService tutorService, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.tutorService = tutorService;
        this.passwordEncoder = passwordEncoder;
    }

    public User getByUserName(String userName) {
        return userDao.findUserByEmail(userName);
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

    public User signUp(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDao.save(user);
    }

    public UserAuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest) {

        User user = getByUserName(userAuthenticationRequest.getUserName());
        UserAuthenticationResponse response = new UserAuthenticationResponse(user.getFirstName(), user.getLastName(), user.getUserRole(), user.getEmail());
        response.setAuthenticated(true);


        if(StringUtils.isEmpty(userAuthenticationRequest.getUserName())  || StringUtils.isEmpty(userAuthenticationRequest.getPassword())) {
            response.setAuthenticated(false);
        } else if(!passwordEncoder.matches(userAuthenticationRequest.getPassword(), user.getPassword())) {
            response.setAuthenticated(false);
        }

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // assume username is email
        User user = getByUserName(userName);

        return null; //new APIAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>());

    }
}
