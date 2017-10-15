package com.grasp.controller;


import com.grasp.model.Tutor;
import com.grasp.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Tutor> getAllTutors() {
        return tutorService.getAllTutors();
    }

}
