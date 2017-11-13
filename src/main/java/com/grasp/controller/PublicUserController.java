package com.grasp.controller;

import com.grasp.model.dto.InUseDTO;
import com.grasp.model.entity.User;
import com.grasp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/user")
public class PublicUserController {

    private UserService userService;

    @Autowired
    public PublicUserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<InUseDTO> getByName(@PathVariable("email") String email) {
        System.out.println("EMAIL: " + email);
        User user = userService.getByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(new InUseDTO(false, "Email " + email + " not in use."), HttpStatus.OK);
        }

        return new ResponseEntity<>(new InUseDTO(true, "Email " + email + " in use."), HttpStatus.OK);
    }
}
