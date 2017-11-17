package com.grasp.service;

import com.grasp.dao.UserDao;
import com.grasp.exception.ServiceException;
import com.grasp.model.entity.User;
import com.grasp.security.model.UserAuthenticationRequest;
import com.grasp.security.model.UserAuthenticationResponse;
import com.grasp.util.CollectionHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {


    private static final int MINIMUM_PASSWORD_LENGTH = 5;
    // Regex taken from : http://emailregex.com/ Conforms to RFC 5322
//    private static final Pattern EMAIL_REGEX = Pattern.compile();

    private UserDao userDao;
    private BCryptPasswordEncoder passwordEncoder;
    private ElasticsearchService elasticsearchService;

    @Autowired
    public UserService(UserDao userDao, BCryptPasswordEncoder passwordEncoder,
                       ElasticsearchService elasticsearchService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.elasticsearchService = elasticsearchService;
    }

    public User getByEmail(String email) {
        return userDao.findUserByEmail(email);
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

    private void updateUserFields(User originalUser, User updatedUser) {
        String firstName = updatedUser.getFirstName();
        String lastName = updatedUser.getLastName();
        String program = updatedUser.getProgram();
        String faculty = updatedUser.getFaculty();
        String email = updatedUser.getEmail();
        int year = updatedUser.getYear();

        if (email != null && getByEmail(email) == null) {
            originalUser.setEmail(email);
        }
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

    private void validatePassword(String password) {
        if (password == null || password.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "ERROR: Password does not conform to minimum requirements.");
        }
    }

//    private void validateEmail(String email) {
//
//        Matcher matcher = EMAIL_REGEX.matcher(email);
//
//        if (!matcher.find()) {
//            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR,
//                    "ERROR: Email " + email + " does not match regex");
//        }
//
//    }

    private String buildImageUrl(String firstName, String lastName) {

        StringBuilder sb = new StringBuilder(User.IMAGE_URL_PREFIX);

        if (StringUtils.isEmpty(firstName)) {
            sb.append(firstName);
            sb.append(User.IMAGE_URL_NAME_DELIMITER);
        }

        if (StringUtils.isEmpty(lastName)) {
            sb.append(lastName);
        }

        return sb.toString();
    }


    public User updateUser(User user) {

        User originalUser = getById(user.getId());
        if (originalUser == null) {
            return null;
        }

        updateUserFields(originalUser, user);

        originalUser.setImageUrl(buildImageUrl(originalUser.getFirstName(), originalUser.getLastName()));

//        validateEmail(originalUser.getEmail());

        // if user is a tutor - need to update info in es
        if (originalUser.getUserType() == User.UserType.TUTOR) {
            elasticsearchService.upsertTutor(originalUser);
        }

        return userDao.save(originalUser);
    }

    public User signUp(User user) {

        validatePassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setImageUrl(buildImageUrl(user.getFirstName(), user.getLastName()));

//        validateEmail(user.getEmail());


        if (getByEmail(user.getEmail()) != null) {
            return null;
        }

        return userDao.save(user);
    }

    public UserAuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest) {

        User user = getByEmail(userAuthenticationRequest.getUserName());

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
