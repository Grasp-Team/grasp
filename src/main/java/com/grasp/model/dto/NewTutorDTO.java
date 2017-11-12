package com.grasp.model.dto;


import com.grasp.dao.CourseCatalogDao;
import com.grasp.model.CourseCatalog;
import com.grasp.model.Tutor;
import lombok.Getter;
import lombok.Setter;

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

    public List<Tutor> convertToEntity(CourseCatalogDao courseCatalogDao) {
        List<Tutor> tutorCourseEntries = new ArrayList<>();

        for (String course : courseCodes) {
            CourseCatalog courseCatalog = courseCatalogDao.findByCode(course);

            // Error out if a given course is not found
            if (courseCatalog == null) {
                return null;
            }

            Tutor newCourseEntry = new Tutor(this.userId, courseCatalog);
            tutorCourseEntries.add(newCourseEntry);
        }

        return tutorCourseEntries;
    }

}
