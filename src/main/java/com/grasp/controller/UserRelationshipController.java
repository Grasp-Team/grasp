package com.grasp.controller;

import com.grasp.model.UserRelationship;
import com.grasp.model.dto.EntityConverter;
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

    private UserRelationshipService relationshipService;
    private EntityConverter entityConverter;

    @Autowired
    public UserRelationshipController(UserRelationshipService relationshipService, EntityConverter entityConverter) {
        this.relationshipService = relationshipService;
        this.entityConverter = entityConverter;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<UserRelationshipDTO> createRelationship(
            @RequestBody UserRelationshipDTO userRelationshipDTO) {
        UserRelationship newRelationship = relationshipService.addNewRelationship(entityConverter
                .convertToEntity(userRelationshipDTO));

        return new ResponseEntity<>(entityConverter.convertToDTO(newRelationship), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<UserRelationshipDTO> updateRelationship(
            @RequestBody UserRelationshipDTO userRelationshipDTO) {
        UserRelationship relationship = relationshipService.updateExistingRelationship(entityConverter
                .convertToEntity(userRelationshipDTO));

        if (relationship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(relationship), HttpStatus.OK);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity<UserRelationshipDTO> getRelationship(@RequestBody UserRelationshipDTO userRelationshipDTO) {
        UserRelationship relationship = relationshipService.getRelationshipStatus(entityConverter
                .convertToEntity(userRelationshipDTO));

        if (relationship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(relationship), HttpStatus.OK);

    }


}
