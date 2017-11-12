package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListDTO {

    private List<UserDTO> users;

    public UserListDTO() {
    }

    public UserListDTO(List<UserDTO> users) {
        this.users = users;
    }
}
