package com.grasp.model.dto;

import com.grasp.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListDTO {

    private List<User> users;

    public UserListDTO() {
    }

    public UserListDTO(List<User> users) {
        this.users = users;
    }
}
