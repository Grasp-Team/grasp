package com.grasp.service;

import com.grasp.dao.CourseCatalogDao;
import com.grasp.dao.TutorDao;
import com.grasp.dao.UserDao;
import com.grasp.exception.ServiceException;
import com.grasp.model.entity.CourseCatalog;
import com.grasp.model.entity.Tutor;
import com.grasp.model.entity.User;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ElasticsearchService elasticsearchService;

    @Autowired
    public TutorService(TutorDao tutorDao, UserDao userDao, CourseCatalogDao courseCatalogDao, ElasticsearchService elasticsearchService) {
        this.tutorDao = tutorDao;
        this.userDao = userDao;
        this.courseCatalogDao = courseCatalogDao;
        this.elasticsearchService = elasticsearchService;
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

        if(tutor == null) {
            return null;
        }

        UUID id = tutor.getId();

        List<Tutor> tutorEntries = courseCatalogEntries.stream().map(c -> new Tutor(id, c)).collect(
                Collectors.toList());

        if (CollectionHelper.isEmpty(tutorEntries)) {
            return null;
        }

        // blank tutor entries
        tutor.setTutors(new ArrayList<>());
        tutor = userDao.save(tutor);

        tutor.setUserType(User.UserType.TUTOR);
        tutor.setTutors(tutorEntries);

        User user = userDao.save(tutor);

        elasticsearchService.upsertTutor(user);

        return user;
    }

    public User unregisterTutor(UUID tutorId) {

        User tutor = userDao.findUserById(tutorId);

        if(tutor == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "ERROR: tutor " + tutorId + "does not exist");
        }

        tutor.setUserType(User.UserType.STANDARD);
        tutor.setTutors(new ArrayList<>());

        User user = userDao.save(tutor);

        elasticsearchService.deleteTutor(tutorId);

        return user;
    }

    public User updateCoursesForTutor(UUID userId, List<String> courseCodes) {
        User tutor = userDao.findUserById(userId);

        if (tutor == null || tutor.getUserType() != User.UserType.TUTOR) {
            return null;
        }

        tutorDao.deleteAllByUid(userId);

        List<CourseCatalog> courseCatalogEntries = courseCatalogDao.findAllByCodeIn(courseCodes);
        List<Tutor> tutorEntries = courseCatalogEntries.stream().map(c -> new Tutor(userId, c)).collect(
                Collectors.toList());

        tutor.setTutors(tutorEntries);
        tutor = userDao.save(tutor);

        elasticsearchService.upsertTutor(tutor);

        return tutor;
    }

    public User deleteCoursesForTutor(UUID userId) {

        User tutor = userDao.findUserById(userId);

        if(tutor == null || tutor.getUserType() != User.UserType.TUTOR) {
            return null;
        }

        tutorDao.deleteAllByUid(userId);

        tutor.setTutors(new ArrayList<>());
        tutor = userDao.save(tutor);

        elasticsearchService.upsertTutor(tutor);

        return tutor;
    }
}
