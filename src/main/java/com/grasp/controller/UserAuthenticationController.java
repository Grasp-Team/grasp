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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserAuthenticationController {

    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserAuthenticationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<User> signUp(@RequestBody UserSignUpDTO userDTO) {

        User user = userService.signUp(UserSignUpDTO.convertToEntity(userDTO, modelMapper));

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

}
