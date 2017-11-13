package com.grasp.util;

import com.grasp.model.entity.CourseCatalog;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalogTestUtil {

    public static final CourseCatalog COURSE_1 = new CourseCatalog("CS123", "CS", 123, "Computer Science", "Description", "undergraduate", 2017,
            "url.com");

    public static final CourseCatalog COURSE_2 = new CourseCatalog("AMATH123", "AMATH", 123, "Applied Math", "Description", "graduate", 2017,
            "url.com");

    public static final CourseCatalog COURSE_3 = new CourseCatalog("CS245", "CS", 123, "COMPUTER SCIENE", "Description", "graduate", 2017,
            "url.com");

    public static final List<CourseCatalog> COURSE_CATALOG_LIST = new ArrayList<>();

    public static final List<CourseCatalog> COURSE_CATALOG_LIST_CS = new ArrayList<>();

    static {
        COURSE_CATALOG_LIST.add(COURSE_1);
        COURSE_CATALOG_LIST.add(COURSE_2);
        COURSE_CATALOG_LIST.add(COURSE_3);

        COURSE_CATALOG_LIST_CS.add(COURSE_1);
        COURSE_CATALOG_LIST_CS.add(COURSE_3);
    }
}
