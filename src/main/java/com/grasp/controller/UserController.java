package com.grasp.controller;

import com.grasp.model.User;
import com.grasp.model.dto.AuthenticationDTO;
import com.grasp.model.dto.UserSignUpDTO;
import com.grasp.security.model.UserAuthenticationResponse;
import com.grasp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> getByName(@PathVariable("email") String email) {
        User user = userService.getByUserName(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable("id") UUID uid) {

        User user = userService.getById(uid);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody UserSignUpDTO userDTO) {

        User user = userService.updateUser(convertToEntity(userDTO));

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<User> signUp(@RequestBody UserSignUpDTO userDTO) {

        User user = userService.signUp(convertToEntity(userDTO));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<UserAuthenticationResponse> authentication(@RequestBody AuthenticationDTO authenticationDTO, HttpServletRequest request, HttpServletResponse response) {

        UserAuthenticationResponse authenticationResponse = new UserAuthenticationResponse();

        try {

            authenticationManager

        } catch(InternalAuthenticationServiceException e) {

        } catch(AuthenticationException e){

        }

    }

    @RequestMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    //TODO: Put these somewhere better
    private User convertToEntity(UserSignUpDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private
}
