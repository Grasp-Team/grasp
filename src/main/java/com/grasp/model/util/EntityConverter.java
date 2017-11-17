package com.grasp.model.util;

import com.grasp.model.dto.*;
import com.grasp.model.entity.Subject;
import com.grasp.model.entity.User;
import com.grasp.model.entity.UserRelationship;
import com.grasp.model.entity.UserSubject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EntityConverter {

    private ModelMapper modelMapper;

    @Autowired
    public EntityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToEntity(UserSignUpDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserListDTO convertToDTO(List<User> users) {
        return new UserListDTO(users.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    public UserRelationship convertToEntity(UserRelationshipDTO userRelationshipDTO) {
        return modelMapper.map(userRelationshipDTO, UserRelationship.class);
    }

    public UserRelationshipDTO convertToDTO(UserRelationship userRelationship) {
        return modelMapper.map(userRelationship, UserRelationshipDTO.class);
    }

    private SubjectDTO convertToDTO(Subject subject) {
        return modelMapper.map(subject, SubjectDTO.class);
    }

    public SubjectListDTO convertToSubjectListDTO(List<Subject> subjects) {
        return new SubjectListDTO(subjects.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    public List<UserSubject> convertToEntity(UserSubjectDTO userSubjectDTO) {
        UUID userId = userSubjectDTO.getUserId();

        return userSubjectDTO.getSubjects().stream().map(s -> new UserSubject(userId, s))
                             .collect(Collectors.toList());
    }

    public UserRelationshipListDTO convertToUserRelationshipDTO(List<UserRelationship> userRelationships) {
        List<UserRelationshipDTO> relationshipList = new ArrayList<>();

        for (UserRelationship relationship : userRelationships) {
            relationshipList.add(convertToDTO(relationship));
        }

        return new UserRelationshipListDTO(relationshipList);
    }

    public UserSubjectDTO convertToUserSubjectDTO(List<UserSubject> userSubjects) {
        List<String> subjectList = new ArrayList<>();

        for (UserSubject userSubject : userSubjects) {
            subjectList.add(userSubject.getSubject());
        }

        return new UserSubjectDTO(userSubjects.get(0).getUserId(), subjectList);
    }

}
