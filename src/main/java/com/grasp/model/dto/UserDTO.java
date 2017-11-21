package com.grasp.model.dto;

import com.grasp.model.entity.Tutor;
import com.grasp.model.entity.User;
import com.grasp.security.model.UserRole;
import io.searchbox.annotations.JestId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    @JestId
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private int year;
    private String program;
    private String faculty;
    private String imageUrl;
    private User.UserType userType;
    private UserRole userRole;
    private List<Tutor> tutors;

    public UserDTO() {
    }
}
