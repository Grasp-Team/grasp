package com.grasp.controller;

import com.grasp.model.Subject;
import com.grasp.model.UserSubject;
import com.grasp.model.util.EntityConverter;
import com.grasp.model.dto.SubjectListDTO;
import com.grasp.model.dto.UserSubjectDTO;
import com.grasp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private EntityConverter entityConverter;
    private SubjectService subjectService;

    @Autowired
    public SubjectController(EntityConverter entityConverter, SubjectService subjectService) {
        this.entityConverter = entityConverter;
        this.subjectService = subjectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<SubjectListDTO> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();

        if (subjectList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entityConverter.convertToSubjectListDTO(subjectList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserSubjectDTO> addSubjectsForUser(@RequestBody UserSubjectDTO userSubjectDTO) {
        List<UserSubject> subjects = subjectService.addSubjectsForUser(entityConverter.convertToEntity(userSubjectDTO));
        return new ResponseEntity<>(entityConverter.convertToUserSubjectDTO(subjects), HttpStatus.OK);
    }
}
