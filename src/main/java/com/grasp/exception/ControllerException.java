package com.grasp.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ControllerException extends GraspRuntimeException {

    public ControllerException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public ControllerException(HttpStatus httpStatus, List<String> errors) {
        super(httpStatus, errors);
    }
}
