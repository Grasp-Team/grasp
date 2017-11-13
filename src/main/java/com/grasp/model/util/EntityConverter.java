package com.grasp.model.util;

import com.grasp.dao.UserDao;
import com.grasp.model.Subject;
import com.grasp.model.User;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.dto.UserListDTO;
import com.grasp.model.dto.UserRelationshipDTO;
import com.grasp.model.dto.UserSignUpDTO;
import com.grasp.model.UserRelationship;
import com.grasp.model.UserSubject;
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
    private UserDao userDao;

    @Autowired
    public EntityConverter(ModelMapper modelMapper, UserDao userDao) {
        this.modelMapper = modelMapper;
        this.userDao = userDao;
    }

    public User convertToEntity(UserSignUpDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        user.setPassword(userDao.findUserById(user.getId()).getPassword());

        return user;
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
        List<String> subjects = userSubjectDTO.getSubjects();
        UUID userId = userSubjectDTO.getUserId();

        List<UserSubject> userSubjects = new ArrayList<>();

        for (String subject : subjects) {
            userSubjects.add(new UserSubject(userId, subject));
        }

        return userSubjects;
    }

    public UserSubjectDTO convertToUserSubjectDTO(List<UserSubject> userSubjects) {
        List<String> subjectList = new ArrayList<>();

        for (UserSubject userSubject : userSubjects) {
            subjectList.add(userSubject.getSubject());
        }

        return new UserSubjectDTO(userSubjects.get(0).getUserId(), subjectList);
    }

}
