package com.grasp.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExceptionDTO {

    private String message;
    private List<String> errors = new ArrayList<>();

    public ExceptionDTO(String message, List<String> errors) {
        this.message = message;
        this.errors.addAll(errors);
    }

    public ExceptionDTO(String message, String error) {
        this.message = message;
        this.errors.add(error);
    }
}
