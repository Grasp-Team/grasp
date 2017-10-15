package com.grasp.service;


import com.grasp.dao.CourseCatalogDao;
import com.grasp.model.CourseCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseCatalog")
public class CourseCatalogService {

    private CourseCatalogDao courseCatalogDao;

    @Autowired
    public CourseCatalogService(CourseCatalogDao courseCatalogDao) {
        this.courseCatalogDao = courseCatalogDao;
    }

    @RequestMapping("/")
    public List<CourseCatalog> getAllCourses() {
        return (List<CourseCatalog>) courseCatalogDao.findAll();
    }

    @RequestMapping("/code/{code}")
    public CourseCatalog getCourseByCode(@PathVariable("code") String code) {
        return courseCatalogDao.findByCode(code);
    }

    @RequestMapping("/subject/{subject}")
    public List<CourseCatalog> getCoursesBySubject(@PathVariable("subject") String subject) {
        return courseCatalogDao.findAllBySubject(subject);
    }

}
