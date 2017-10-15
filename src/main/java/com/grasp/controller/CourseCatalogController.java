package com.grasp.controller;


import com.grasp.service.CourseCatalogService;
import com.grasp.model.CourseCatalog;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CourseCatalog>> getAllCourses() {
        return new ResponseEntity<>(courseCatalogService.getAllCourses(), HttpStatus.OK);
    }

    @RequestMapping("/code/{code}")
    public ResponseEntity<CourseCatalog> getCourseByCode(@PathVariable("code") String code) {
        CourseCatalog courseCatalog = courseCatalogService.getCourseByCode(code);

        if(courseCatalog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courseCatalog, HttpStatus.OK);
    }

    @RequestMapping("/subject/{subject}")
    public ResponseEntity<List<CourseCatalog>> getCoursesBySubject(@PathVariable("subject") String subject) {
        List<CourseCatalog> courseCatalogs = courseCatalogService.getCoursesBySubject(subject);

        if(CollectionHelper.isEmpty(courseCatalogs)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(courseCatalogs, HttpStatus.OK);
    }

}
