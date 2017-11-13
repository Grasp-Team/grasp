package com.grasp.service;

import com.grasp.dao.SubjectDao;
import com.grasp.dao.UserSubjectDao;
import com.grasp.model.Subject;
import com.grasp.model.UserSubject;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {

    private SubjectDao subjectDao;
    private UserSubjectDao userSubjectDao;

    @Autowired
    public SubjectService(SubjectDao subjectDao, UserSubjectDao userSubjectDao) {
        this.subjectDao = subjectDao;
        this.userSubjectDao = userSubjectDao;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = (List<Subject>) subjectDao.findAll();

        if (CollectionHelper.isEmpty(subjects)) {
            return new ArrayList<>();
        }

        return subjects;
    }

    public List<UserSubject> addSubjectsForUser(List<UserSubject> userSubjects) {
        return (List<UserSubject>) userSubjectDao.save(userSubjects);
    }

    public List<UserSubject> updateSubjectsForUser(UUID userId, List<UserSubject> updatedUserSubjects) {

        if (CollectionHelper.isEmpty(userSubjectDao.findAllByUserId(userId))) {
            return new ArrayList<>();
        }

        userSubjectDao.deleteAllByUserId(userId);
        return (List<UserSubject>) userSubjectDao.save(updatedUserSubjects);
    }

}
