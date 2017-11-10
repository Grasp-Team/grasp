package com.grasp.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationRequest {

    private String userName;
    private String password;

    public UserAuthenticationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
