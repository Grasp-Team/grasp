package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {

    private String userName;
    private String password;

    public AuthenticationDTO() {
    }
}
