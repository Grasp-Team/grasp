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

@Service
public class TutorService {

    private TutorDao tutorDao;
    private UserDao userDao;

    @Autowired
    public TutorService(TutorDao tutorDao, UserDao userDao) {
        this.tutorDao = tutorDao;
        this.userDao = userDao;
    }

    public List<Tutor> getAllTutorEntries() {
        List<Tutor> tutors = (List<Tutor>) tutorDao.findAll();

        if (CollectionHelper.isEmpty(tutors)) {
            return new ArrayList<>();
        }

        return tutors;
    }

    public List<User> getAllTutors() {

        List<User> tutors = userDao.findAllByUserType(User.UserType.TUTOR);

        if (CollectionHelper.isEmpty(tutors)) {
            return new ArrayList<>();
        }

        return tutors;
    }

    public List<User> getAllTutorsApplicableToCourse(String courseCode) {
        List<Tutor> tutors = tutorDao.findTutorsByCourseCatalog_Code(courseCode);

        if (CollectionHelper.isEmpty(tutors)) {
            return new ArrayList<>();
        }

        List<User> users = new ArrayList<>();
        for (Tutor tutor : tutors) {
            User user = userDao.findUserById(tutor.getUid());
            users.add(user);
        }

        return users;
    }

}
