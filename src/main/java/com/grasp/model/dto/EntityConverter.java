package com.grasp.model.dto;

import com.grasp.dao.UserDao;
import com.grasp.model.User;
import com.grasp.model.UserRelationship;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

}
