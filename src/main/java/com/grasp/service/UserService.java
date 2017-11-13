package com.grasp.service;

import com.grasp.dao.UserDao;
import com.grasp.exception.ServiceException;
import com.grasp.model.User;
import com.grasp.security.model.APIAuthenticationToken;
import com.grasp.security.model.UserAuthenticationRequest;
import com.grasp.security.model.UserAuthenticationResponse;
import com.grasp.util.CollectionHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
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

        if (CollectionHelper.isEmpty(users)) {
            return new ArrayList<>();
        }

        return users;
    }

    /*
     * Updates one User object with the non-null fields of another User object
     */
    private void updateUserFields(User originalUser, User updatedUser) {
        String firstName = updatedUser.getFirstName();
        String lastName = updatedUser.getLastName();
        String program = updatedUser.getProgram();
        String faculty = updatedUser.getFaculty();
        int year = updatedUser.getYear();

        if (firstName != null) {
            originalUser.setFirstName(firstName);
        }
        if (lastName != null) {
            originalUser.setLastName(lastName);
        }
        if (program != null) {
            originalUser.setProgram(program);
        }
        if (faculty != null) {
            originalUser.setFaculty(faculty);
        }
        if (year != 0) {
            originalUser.setYear(year);
        }

    }

    /*
     * Updates basic user information (i.e. name, program, faculty, etc...)
     */
    public User updateUser(User user) {

        // Check if user exists and return null otherwise
        User originalUser = getById(user.getId());
        if (originalUser == null) {
            return null;
        }

        // Update applicable fields
        updateUserFields(originalUser, user);

        // Save and return
        return userDao.save(originalUser);
    }

    public User signUp(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userDao.findUserByEmail(user.getEmail()) != null) {
            return null;
        }

        return userDao.save(user);
    }

    public UserAuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest) {

        User user = getByUserName(userAuthenticationRequest.getUserName());

        if (user == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND,
                    "ERROR: Unable to find: " + userAuthenticationRequest.getUserName());
        }

        UserAuthenticationResponse response = new UserAuthenticationResponse(user.getFirstName(), user.getLastName(),
                user.getUserRole(), user.getEmail());
        response.setAuthenticated(true);


        if (StringUtils.isEmpty(userAuthenticationRequest.getUserName()) || StringUtils
                .isEmpty(userAuthenticationRequest.getPassword())) {
            response.setAuthenticated(false);
        } else if (!passwordEncoder.matches(userAuthenticationRequest.getPassword(), user.getPassword())) {
            response.setAuthenticated(false);
        }

        return response;
    }
}
