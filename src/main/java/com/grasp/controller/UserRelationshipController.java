package com.grasp.controller;

import com.grasp.exception.ControllerException;
import com.grasp.model.dto.UserRelationshipListDTO;
import com.grasp.model.entity.UserRelationship;
import com.grasp.model.util.EntityConverter;
import com.grasp.model.dto.UserRelationshipDTO;
import com.grasp.service.UserRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<UserRelationshipDTO> updateRelationship(@PathVariable("id") Long id,
                                                                  @RequestBody UserRelationshipDTO status) {

        UserRelationship relationship = relationshipService
                .updateExistingRelationship(id, status.getRelationshipStatus());

        return new ResponseEntity<>(entityConverter.convertToDTO(relationship), HttpStatus.OK);
    }

    @RequestMapping(value = "/tutor", method = RequestMethod.POST)
    public ResponseEntity<UserRelationshipDTO> getRelationshipForTutorAndUser(
            @RequestBody UserRelationshipDTO userRelationshipDTO) {

        UUID tutor = userRelationshipDTO.getTutorId();
        UUID user = userRelationshipDTO.getUserId();

        if (tutor == null) {
            throw new ControllerException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: no provided tutor.");
        } else if (user == null) {
            throw new ControllerException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: no provided user.");
        }

        UserRelationship relationship = relationshipService.getRelationshipByUserAndTutor(tutor, tutor);

        if (relationship == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND,
                    "Unable to find relationship for tutor: " + tutor + " and user: " + user);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(relationship), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserRelationshipDTO> getRelationship(@PathVariable("id") Long id) {
        UserRelationship relationship = relationshipService.getRelationshipById(id);

        if (relationship == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "Unable to find relationship: " + id);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(relationship), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRelationshipById(@PathVariable("id") Long id) {
        relationshipService.deleteRelationship(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tutor/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserRelationshipListDTO> getTutorRelationshipById(@PathVariable("id") UUID tutorId,
                                                                            @RequestParam(value = "status", defaultValue = "") String status) {
        return new ResponseEntity<>(
                entityConverter
                        .convertToUserRelationshipDTO(relationshipService.getRelationshipByTutor(tutorId, status)),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserRelationshipListDTO> getUserRelationshipById(@PathVariable("id") UUID userId,
                                                                           @RequestParam(value = "status", defaultValue = "") String status) {

        return new ResponseEntity<>(
                entityConverter.convertToUserRelationshipDTO(relationshipService.getRelationshipByUser(userId, status)),
                HttpStatus.OK);
    }
}
