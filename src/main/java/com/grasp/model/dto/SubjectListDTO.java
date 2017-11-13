package com.grasp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectListDTO {

    private List<SubjectDTO> subjects;

    public SubjectListDTO() {
    }

    public SubjectListDTO(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }
}
