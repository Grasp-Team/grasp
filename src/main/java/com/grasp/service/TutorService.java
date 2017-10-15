package com.grasp.service;

import com.grasp.dao.TutorDao;
import com.grasp.model.Tutor;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorService {

    private TutorDao tutorDao;

    @Autowired
    public TutorService(TutorDao tutorDao) {
        this.tutorDao = tutorDao;
    }

    public List<Tutor> getAllTutors() {

        List<Tutor> tutors = (List<Tutor>) tutorDao.findAll();

        if(CollectionHelper.isEmpty(tutors)) {
            return new ArrayList<>();
        }

        return tutors;
    }

}
