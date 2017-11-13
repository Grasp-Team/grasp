package com.grasp.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceException extends GraspRuntimeException {

    public ServiceException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public ServiceException(HttpStatus httpStatus, List<String> errors) {
        super(httpStatus, errors);
    }

}
