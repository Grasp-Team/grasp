package com.grasp.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class NewTutorDTO {
    private UUID userId;
    private List<String> courseCodes;

    public NewTutorDTO() {
    }

    public NewTutorDTO(UUID userId, List<String> courseCodes) {
        this.userId = userId;
        this.courseCodes = courseCodes;
    }
}
