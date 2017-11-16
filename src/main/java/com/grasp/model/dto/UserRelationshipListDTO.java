package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRelationshipListDTO {

    private List<UserRelationshipDTO> userRelationshipList;

    public UserRelationshipListDTO() {
    }

    public UserRelationshipListDTO(List<UserRelationshipDTO> userRelationshipList) {
        this.userRelationshipList = userRelationshipList;
    }
}
