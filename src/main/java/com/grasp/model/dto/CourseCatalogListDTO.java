package com.grasp.model.dto;

import com.grasp.model.entity.CourseCatalog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseCatalogListDTO {

    private List<CourseCatalog> courseCatalogs;

    public CourseCatalogListDTO(List<CourseCatalog> courseCatalogs) {
        this.courseCatalogs = courseCatalogs;
    }

    public CourseCatalogListDTO() {
    }
}
