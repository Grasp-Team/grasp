package com.grasp.controller;

import com.grasp.model.User;
import com.grasp.model.dto.EntityConverter;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.dto.UserListDTO;
import com.grasp.model.dto.UserSignUpDTO;
import com.grasp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getById(@PathVariable("id") UUID uid) {

        User user = userService.getById(uid);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    // TODO: think about merging these two
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserSignUpDTO userDTO) {

        User user = userService.updateUser(entityConverter.convertToEntity(userDTO));

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> signUp(@RequestBody UserSignUpDTO userDTO) {

        User user = userService.signUp(entityConverter.convertToEntity(userDTO));

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping()
    public ResponseEntity<UserListDTO> getAllUsers() {
        return new ResponseEntity<>(entityConverter.convertToDTO(userService.getAllUsers()), HttpStatus.OK);
    }
}
