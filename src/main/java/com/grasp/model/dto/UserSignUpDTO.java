package com.grasp.model.dto;

import com.grasp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class UserSignUpDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int year;
    private String program;
    private String faculty;

    public UserSignUpDTO() {
    }

    public UserSignUpDTO(String email, String password, String firstName, String lastName, int year, String program, String faculty) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.program = program;
        this.faculty = faculty;
    }
}
