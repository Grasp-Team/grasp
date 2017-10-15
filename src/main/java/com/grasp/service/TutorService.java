package com.grasp.service;

import com.grasp.dao.TutorDao;
import com.grasp.dao.UserDao;
import com.grasp.model.Tutor;
import com.grasp.model.User;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    private TutorDao tutorDao;
    private UserDao userDao;

    @Autowired
    public TutorService(TutorDao tutorDao, UserDao userDao) {
        this.tutorDao = tutorDao;
        this.userDao = userDao;
    }

    public List<Tutor> getAllTutors() {

        List<Tutor> tutors = (List<Tutor>) tutorDao.findAll();

        if(CollectionHelper.isEmpty(tutors)) {
            return new ArrayList<>();
        }

        return tutors;
    }

    //TODO: probably want to move this elsewhere
    public List<User> getAllTutorsWithUser() {
        return userDao.findAllByIdIn(getAllTutors().stream().map(Tutor::getUid).collect(Collectors.toList()));
    }

}
