package com.grasp.controller;


import com.grasp.exception.ControllerException;
import com.grasp.model.dto.CourseCatalogListDTO;
import com.grasp.service.CourseCatalogService;
import com.grasp.model.CourseCatalog;
import com.grasp.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.Control;
import java.util.ArrayList;
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
    public ResponseEntity<CourseCatalogListDTO> getAllCourses() {
        List<CourseCatalog> courses = courseCatalogService.getAllCourses();

        if (courses == null) {
            courses = new ArrayList<>();
        }

        return new ResponseEntity<>(new CourseCatalogListDTO(courses), HttpStatus.OK);
    }

    @RequestMapping("/code/{code}")
    public ResponseEntity<CourseCatalog> getCourseByCode(@PathVariable("code") String code) {
        if (code == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "ERROR: Code cannot be null");
        }

        CourseCatalog courseCatalog = courseCatalogService.getCourseByCode(code);

        if (courseCatalog == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "ERROR: Cannot find: " + code);
        }

        return new ResponseEntity<>(courseCatalog, HttpStatus.OK);
    }

    @RequestMapping("/subject/{subject}")
    public ResponseEntity<CourseCatalogListDTO> getCoursesBySubject(@PathVariable("subject") String subject) {
        if(subject == null) {
            throw new ControllerException(HttpStatus.NOT_FOUND, "ERROR: Subject cannot be null");
        }

        List<CourseCatalog> courseCatalogs = courseCatalogService.getCoursesBySubject(subject);

        if (CollectionHelper.isEmpty(courseCatalogs)) {
            throw new ControllerException(HttpStatus.NOT_FOUND,
                    "ERROR: Did not find courses for subject: " + subject);
        }

        return new ResponseEntity<>(new CourseCatalogListDTO(courseCatalogs), HttpStatus.OK);
    }

}
