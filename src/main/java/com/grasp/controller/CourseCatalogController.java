package com.grasp.controller;


import com.grasp.service.CourseCatalogService;
import com.grasp.model.CourseCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseCatalog")
public class CourseCatalogController {

    private CourseCatalogService courseCatalogService;

    @Autowired
    public CourseCatalogController(CourseCatalogService courseCatalogService) {
        this.courseCatalogService = courseCatalogService;
    }

    @RequestMapping()
    public List<CourseCatalog> getAllCourses() {
        return courseCatalogService.getAllCourses();
    }

    @RequestMapping("/code/{code}")
    public CourseCatalog getCourseByCode(@PathVariable("code") String code) {
        return courseCatalogService.getCourseByCode(code);
    }

    @RequestMapping("/subject/{subject}")
    public List<CourseCatalog> getCoursesBySubject(@PathVariable("subject") String subject) {
        return courseCatalogService.getCoursesBySubject(subject);
    }

}
