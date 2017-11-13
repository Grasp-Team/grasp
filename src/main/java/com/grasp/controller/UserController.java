package com.grasp.controller;

import com.grasp.exception.ControllerException;
import com.grasp.model.User;
import com.grasp.model.util.EntityConverter;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.dto.UserListDTO;
import com.grasp.model.dto.UserSignUpDTO;
import com.grasp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private EntityConverter entityConverter;

    @Autowired
    public UserController(UserService userService, EntityConverter entityConverter) {
        this.userService = userService;
        this.entityConverter = entityConverter;
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getByName(@PathVariable("email") String email) {
        User user = userService.getByUserName(email);

        if (user == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "ERROR: Unable to find user with email: " + email);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getById(@PathVariable("id") UUID uid) {

        User user = userService.getById(uid);

        if (user == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "ERROR: Unable to find user with id: " + uid);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping( value = "/{id}",method = RequestMethod.POST)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") UUID uid, @RequestBody UserDTO userDTO) {

        User user = entityConverter.convertToEntity(userDTO);

        user.setId(uid);

        if(userService.getById(uid) == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "ERROR: Unable to find user with id: " + uid);
        }

        user = userService.updateUser(user);

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> signUp(@RequestBody UserSignUpDTO userDTO) {

        User user = userService.signUp(entityConverter.convertToEntity(userDTO));

        if(user == null) {
            throw new ControllerException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: User, " + userDTO.getEmail() + " already exists");
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping()
    public ResponseEntity<UserListDTO> getAllUsers() {
        return new ResponseEntity<>(entityConverter.convertToDTO(userService.getAllUsers()), HttpStatus.OK);
    }
}
