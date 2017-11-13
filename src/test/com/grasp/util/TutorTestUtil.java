package com.grasp.util;

import com.grasp.model.entity.CourseCatalog;
import com.grasp.model.entity.Tutor;
import com.grasp.model.entity.User;
import com.grasp.security.model.UserRole;

import java.util.*;

import static com.grasp.util.CourseCatalogTestUtil.COURSE_CATALOG_LIST;

public class TutorTestUtil {

    private static final Random random = new Random(System.currentTimeMillis());
    private static long counter = 0;

    public static final User TUTOR_1 = new User("Jacob", "Moore", "uw5@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.TUTOR, UserRole.STANDARD);

    public static final User TUTOR_2 = new User("Jitin", "Dodd", "uw6@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.TUTOR, UserRole.STANDARD);

    public static final User TUTOR_3 = new User("Charles", "Bai", "uw7@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.TUTOR, UserRole.STANDARD);

    public static final User TUTOR_4 = new User("Josh", "Carnide", "uw8@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.TUTOR, UserRole.STANDARD);

    public static final List<Tutor> TUTOR_COURSE_LIST_1 = new ArrayList<>();
    public static final List<Tutor> TUTOR_COURSE_LIST_2 = new ArrayList<>();
    public static final List<Tutor> TUTOR_COURSE_LIST_3 = new ArrayList<>();
    public static final List<Tutor> TUTOR_COURSE_LIST_4 = new ArrayList<>();

    public static final List<User> CS123_TUTORS = new ArrayList<>();

    public static final List<User> TUTOR_LIST = new ArrayList<>();

    static {
        fillTutorList(TUTOR_1, TUTOR_COURSE_LIST_1);
        fillTutorList(TUTOR_2, TUTOR_COURSE_LIST_2);
        fillTutorList(TUTOR_3, TUTOR_COURSE_LIST_3);
        fillTutorList(TUTOR_4, TUTOR_COURSE_LIST_4);

        TUTOR_LIST.add(TUTOR_1);
        TUTOR_LIST.add(TUTOR_2);
        TUTOR_LIST.add(TUTOR_3);
        TUTOR_LIST.add(TUTOR_4);
    }

    private static void fillTutorList(User user, List<Tutor> tutors) {

        UUID uuid = UUID.randomUUID();

        user.setId(uuid);

        for (int i = 0; i < random.nextInt(3); i++) {
            CourseCatalog courseCatalog = COURSE_CATALOG_LIST.get(random.nextInt(COURSE_CATALOG_LIST.size()));

            if(courseCatalog.equals(CourseCatalogTestUtil.COURSE_1)) {
                CS123_TUTORS.add(user);
            }

            Tutor tutor = new Tutor(uuid, courseCatalog);
            tutor.setTutorId(counter);
            counter++;
            tutors.add(tutor);
        }

        user.setTutors(tutors);
    }

}
