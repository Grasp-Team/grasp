package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserSubjectDTO {

    private UUID userId;
    private List<String> subjects;

    public UserSubjectDTO() {
    }

    public UserSubjectDTO(UUID userId, List<String> subjects) {
        this.userId = userId;
        this.subjects = subjects;
    }
}
