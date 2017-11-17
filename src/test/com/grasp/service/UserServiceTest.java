package com.grasp.service;

import com.grasp.exception.ServiceException;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class UserServiceTest {

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z" +
            "0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\" +
            "x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\" +
            "x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e" +
            "-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*" +
            "[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z" +
            "0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]" +
            "?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-" +
            "9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(" +
            "?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21" +
            "-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
            "\\x0c\\x0e-\\x7f])+)\\])");

    private void validateEmail(String email) {

        Matcher matcher = EMAIL_REGEX.matcher(email);

        if (!matcher.find()) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "ERROR: Email " + email + " does not match regex");
        }

    }

    @Test
    public void test() throws Exception {
        validateEmail("jaemoore@uwaterloo.ca");
    }
}