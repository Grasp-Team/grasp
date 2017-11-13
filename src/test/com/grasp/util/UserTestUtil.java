package com.grasp.util;

import com.grasp.model.User;
import com.grasp.security.model.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserTestUtil {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static final User USER_1 = new User("Jacob", "Moore", "uw1@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.STANDARD, UserRole.STANDARD);

    public static final User USER_2 = new User("Jitin", "Dodd", "uw2@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.STANDARD, UserRole.STANDARD);

    public static final User USER_3 = new User("Charles", "Bai", "uw3@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.STANDARD, UserRole.STANDARD);

    public static final User USER_4 = new User("Josh", "Carnide", "uw4@uwaterloo.ca",
            "$2a$10$izQHCcaO4uvzgd8q8sISuOrejXUU5wlbVlVx2UfGxFhHWQDfFthCK", 4,
            "VP Admin", "Software Engineering", User.UserType.STANDARD, UserRole.STANDARD);

    public static final List<User> USER_LIST = new ArrayList<>();

    static {
        USER_LIST.add(USER_1);
        USER_LIST.add(USER_2);
        USER_LIST.add(USER_3);
        USER_LIST.add(USER_4);
    }
}
