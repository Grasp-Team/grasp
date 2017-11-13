package com.grasp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GraspRuntimeException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private List<String> errors = new ArrayList<>();

    public GraspRuntimeException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        errors.add(message);
    }

    public GraspRuntimeException(HttpStatus httpStatus, List<String> errors) {
        super(errors.toString());
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

}
