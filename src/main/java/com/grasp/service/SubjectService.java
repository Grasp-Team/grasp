package com.grasp.service;

import com.grasp.dao.SubjectDao;
import com.grasp.dao.UserSubjectDao;
import com.grasp.exception.ServiceException;
import com.grasp.model.entity.Subject;
import com.grasp.model.entity.UserSubject;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {

    private UserService userService;
    private SubjectDao subjectDao;
    private UserSubjectDao userSubjectDao;

    @Autowired
    public SubjectService(SubjectDao subjectDao, UserSubjectDao userSubjectDao, UserService userService) {
        this.subjectDao = subjectDao;
        this.userSubjectDao = userSubjectDao;
        this.userService = userService;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = (List<Subject>) subjectDao.findAll();

        if (CollectionHelper.isEmpty(subjects)) {
            return new ArrayList<>();
        }

        return subjects;
    }

    public List<UserSubject> addSubjectsForUser(UUID userId, List<UserSubject> userSubjects) {

        if(userService.getById(userId) == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "ERROR: " + userId + " does not exist");
        }

        return (List<UserSubject>) userSubjectDao.save(userSubjects);
    }

    public List<UserSubject> updateSubjectsForUser(UUID userId, List<UserSubject> updatedUserSubjects) {

        if (CollectionHelper.isEmpty(userSubjectDao.findAllByUserId(userId))) {
            return new ArrayList<>();
        }

        userSubjectDao.deleteAllByUserId(userId);
        return (List<UserSubject>) userSubjectDao.save(updatedUserSubjects);
    }

    public void deleteSubjectsForUser(UUID userId) {

        if(userService.getById(userId) == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "ERROR: " + userId + " does not exist");
        }

        userSubjectDao.deleteAllByUserId(userId);
    }

}
