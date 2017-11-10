package com.grasp.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationResponse {

    private String firstName;
    private String lastName;
    private UserRole userRole;
    private String userName;
    private boolean authenticated = false;

    public UserAuthenticationResponse() {

    }

    public UserAuthenticationResponse(String firstName, String lastName, UserRole userRole, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.userName = userName;
    }
}
