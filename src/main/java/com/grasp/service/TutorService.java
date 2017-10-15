package com.grasp.service;


import com.grasp.dao.TutorDao;
import com.grasp.model.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorService {

    private TutorDao tutorDao;

    @Autowired
    public TutorService(TutorDao tutorDao) {
        this.tutorDao = tutorDao;
    }

    @RequestMapping("/")
    public List<Tutor> getAllTutors() {
        return (List<Tutor>) tutorDao.findAll();
    }

}
