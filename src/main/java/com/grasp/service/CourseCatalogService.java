package com.grasp.service;

import com.grasp.dao.CourseCatalogDao;
import com.grasp.model.CourseCatalog;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseCatalogService {

    private CourseCatalogDao courseCatalogDao;

    @Autowired
    public CourseCatalogService(CourseCatalogDao courseCatalogDao) {
        this.courseCatalogDao = courseCatalogDao;
    }

    // TODO: error handling

    public List<CourseCatalog> getAllCourses() {

        List<CourseCatalog> courseCatalogs = (List<CourseCatalog>) courseCatalogDao.findAll();

        if(CollectionHelper.isEmpty(courseCatalogs)) {
            return new ArrayList<>();
        }

        return courseCatalogs;
    }

    public CourseCatalog getCourseByCode(String code) {
        return courseCatalogDao.findByCode(code);
    }

    public List<CourseCatalog> getCoursesBySubject(String subject) {
        List<CourseCatalog> courseCatalogs = courseCatalogDao.findAllBySubject(subject);

        if(CollectionHelper.isEmpty(courseCatalogs)) {
            return new ArrayList<>();
        }

        return courseCatalogs;
    }
}
