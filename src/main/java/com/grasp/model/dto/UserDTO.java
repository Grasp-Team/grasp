package com.grasp.model.dto;

import com.grasp.model.Tutor;
import com.grasp.model.User;
import com.grasp.security.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private int year;
    private String program;
    private String faculty;
    private User.UserType userType;
    private UserRole userRole;
    private List<Tutor> tutors;

    public UserDTO() {
    }
}
