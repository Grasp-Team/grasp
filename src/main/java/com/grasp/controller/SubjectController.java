package com.grasp.controller;

import com.grasp.model.entity.Subject;
import com.grasp.model.entity.UserSubject;
import com.grasp.model.util.EntityConverter;
import com.grasp.model.dto.SubjectListDTO;
import com.grasp.model.dto.UserSubjectDTO;
import com.grasp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        List<UserSubject> subjects = subjectService.addSubjectsForUser(userSubjectDTO.getUserId(), entityConverter.convertToEntity(userSubjectDTO));

        return new ResponseEntity<>(entityConverter.convertToUserSubjectDTO(subjects), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity<UserSubjectDTO> updateSubjectsForUser(@PathVariable("userId") UUID userId,
                                                                @RequestBody UserSubjectDTO userSubjectDTO) {
        List<UserSubject> userSubjects = subjectService.updateSubjectsForUser(userId,
                entityConverter.convertToEntity(userSubjectDTO));

        if (userSubjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entityConverter.convertToUserSubjectDTO(userSubjects), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubjectsForUser(@PathVariable("userId") UUID userId) {
        subjectService.deleteSubjectsForUser(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
