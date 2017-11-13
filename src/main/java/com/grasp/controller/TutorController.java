package com.grasp.controller;


import com.grasp.model.User;
import com.grasp.model.util.EntityConverter;
import com.grasp.model.dto.NewTutorDTO;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.dto.UserListDTO;
import com.grasp.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    private TutorService tutorService;
    private EntityConverter entityConverter;

    @Autowired
    public TutorController(TutorService tutorService, EntityConverter entityConverter) {
        this.tutorService = tutorService;
        this.entityConverter = entityConverter;
    }

    @RequestMapping()
    public ResponseEntity<UserListDTO> getAllTutors() {
        List<User> tutors = tutorService.getAllTutors();

        if (tutors == null) {
            tutors = new ArrayList<>();
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(tutors), HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{courseCode}", method = RequestMethod.GET)
    public ResponseEntity<UserListDTO> getTutorsByCourseCode(@PathVariable("courseCode") String courseCode) {
        List<User> tutors = tutorService.getAllTutorsApplicableToCourse(courseCode);

        if (tutors.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(tutors), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> registerTutor(@RequestBody NewTutorDTO newTutorDTO) {
        User newTutor = tutorService.registerTutor(newTutorDTO.getUserId(), newTutorDTO.getCourseCodes());

        if (newTutor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(newTutor), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity unregisterTutor(@PathVariable("id") UUID id) {

        User tutor = tutorService.unregisterTutor(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
