package com.grasp.model.dto;


import com.grasp.model.CourseCatalog;
import com.grasp.model.Tutor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
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
