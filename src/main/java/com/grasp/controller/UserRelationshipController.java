package com.grasp.controller;

import com.grasp.model.UserRelationship;
import com.grasp.model.dto.UserRelationshipDTO;
import com.grasp.service.UserRelationshipService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relationship")
public class UserRelationshipController {

    private ModelMapper modelMapper;
    private UserRelationshipService relationshipService;

    @Autowired
    public UserRelationshipController(ModelMapper modelMapper,
                                      UserRelationshipService relationshipService) {
        this.modelMapper = modelMapper;
        this.relationshipService = relationshipService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<UserRelationship> createRelationship(@RequestBody UserRelationshipDTO userRelationshipDTO) {
        UserRelationship newRelationship = relationshipService.addNewRelationship(UserRelationshipDTO
                .convertToEntity(userRelationshipDTO, modelMapper));

        return new ResponseEntity<UserRelationship>(newRelationship, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<UserRelationship> updateRelationship(@RequestBody UserRelationshipDTO userRelationshipDTO) {
        UserRelationship relationship = relationshipService.updateExistingRelationship(UserRelationshipDTO
                .convertToEntity(userRelationshipDTO, modelMapper));

        if (relationship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    // Add endpoint to query a relationship
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<UserRelationship> getRelationship()


}
