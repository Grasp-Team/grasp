package com.grasp.controller;


import com.grasp.model.User;
import com.grasp.model.dto.NewTutorDTO;
import com.grasp.model.dto.UserListDTO;
import com.grasp.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    private TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @RequestMapping()
    public ResponseEntity<UserListDTO> getAllTutors() {
        return new ResponseEntity<>(new UserListDTO(tutorService.getAllTutors()), HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{courseCode}", method = RequestMethod.GET)
    public ResponseEntity<UserListDTO> getTutorsByCourseCode(@PathVariable("courseCode") String courseCode) {
        List<User> tutors = tutorService.getAllTutorsApplicableToCourse(courseCode);

        if (tutors.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserListDTO(tutors), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> registerTutor(@RequestBody NewTutorDTO newTutorDTO) {
        User newTutor = tutorService.registerTutor(newTutorDTO.getUserId(), newTutorDTO.getCourseCodes());

        if (newTutor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newTutor, HttpStatus.OK);
    }

}
