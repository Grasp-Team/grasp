package com.grasp.controller;

import com.grasp.exception.ControllerException;
import com.grasp.model.dto.UserListDTO;
import com.grasp.model.entity.User;
import com.grasp.service.ElasticsearchService;
import com.grasp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/search")
public class SearchController {

    private ElasticsearchService elasticsearchService;
    private UserService userService;

    @Autowired
    public SearchController(ElasticsearchService elasticsearchService, UserService userService) {
        this.elasticsearchService = elasticsearchService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserListDTO> defaultSearch(@PathVariable("id") UUID userId) {

        User user = userService.getById(userId);

        if (user == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "ERROR: Unable to find user: " + userId);
        }

        return new ResponseEntity<>(elasticsearchService.searchTutorBySubject(user.getSubjects()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserListDTO> search(@RequestParam(value = "query", defaultValue = "") String queryString) {
        return new ResponseEntity<>(elasticsearchService.searchTutors(queryString), HttpStatus.OK);
    }
}
