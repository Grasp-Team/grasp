package com.grasp.service;

import com.grasp.dao.CourseCatalogDao;
import com.grasp.dao.TutorDao;
import com.grasp.dao.UserDao;
import com.grasp.model.CourseCatalog;
import com.grasp.model.Tutor;
import com.grasp.model.User;
import com.grasp.model.dto.NewTutorDTO;
import com.grasp.util.CollectionHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TutorService {

    private TutorDao tutorDao;
    private UserDao userDao;
    private CourseCatalogDao courseCatalogDao;
    private ModelMapper modelMapper;

    @Autowired
    public TutorService(TutorDao tutorDao, UserDao userDao, CourseCatalogDao courseCatalogDao,
                        ModelMapper modelMapper) {
        this.tutorDao = tutorDao;
        this.userDao = userDao;
        this.courseCatalogDao = courseCatalogDao;
        this.modelMapper = modelMapper;
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

    public User registerTutor(UUID tutorId, List<String> courseCodes) {
        List<CourseCatalog> courseCatalogEntries = courseCatalogDao.findAllByCodeIn(courseCodes);
        User tutor = userDao.findUserById(tutorId);

        List<Tutor> tutorEntries = courseCatalogEntries.stream().map(c -> new Tutor(tutor.getId(), c)).collect(
                Collectors.toList());

        if (CollectionHelper.isEmpty(tutorEntries) || tutor == null) {
            return null;
        }

        tutor.setUserType(User.UserType.TUTOR);
        tutorDao.save(tutorEntries);
        return userDao.save(tutor);
    }
}
