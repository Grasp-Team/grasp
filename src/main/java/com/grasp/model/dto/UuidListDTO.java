package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UuidListDTO {

    List<UUID> users;

    public UuidListDTO() {
    }

    public UuidListDTO(List<UUID> users) {
        this.users = users;
    }
}
