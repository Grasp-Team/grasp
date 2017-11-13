package com.grasp.controller;

import com.grasp.model.UserRelationship;
import com.grasp.model.util.EntityConverter;
import com.grasp.model.dto.UserRelationshipDTO;
import com.grasp.service.UserRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relationship")
public class UserRelationshipController {

    private UserRelationshipService relationshipService;
    private EntityConverter entityConverter;

    @Autowired
    public UserRelationshipController(UserRelationshipService relationshipService, EntityConverter entityConverter) {
        this.relationshipService = relationshipService;
        this.entityConverter = entityConverter;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserRelationshipDTO> createRelationship(
            @RequestBody UserRelationshipDTO userRelationshipDTO) {
        UserRelationship newRelationship = relationshipService.addNewRelationship(entityConverter
                .convertToEntity(userRelationshipDTO));

        return new ResponseEntity<>(entityConverter.convertToDTO(newRelationship), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<UserRelationshipDTO> updateRelationship(
            @PathVariable("id") Long id, @RequestBody UserRelationship.Status status) {
        UserRelationship relationship = relationshipService.updateExistingRelationship(id, status);
        return new ResponseEntity<>(entityConverter.convertToDTO(relationship), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserRelationshipDTO> getRelationship(@RequestBody UserRelationshipDTO userRelationshipDTO) {
        UserRelationship relationship = relationshipService.getRelationship(entityConverter
                .convertToEntity(userRelationshipDTO));

        if (relationship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(relationship), HttpStatus.OK);

    }
}
