package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {
    private String subject;

    public SubjectDTO() {
    }

    public SubjectDTO(String subject) {
        this.subject = subject;
    }
}
