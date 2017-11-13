package com.grasp.exception;

import com.grasp.exception.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity handleException(ControllerException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ExceptionDTO("Controller threw Exception", e.getErrors()));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleException(ServiceException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ExceptionDTO("Service threw Exception", e.getErrors()));
    }

    @ExceptionHandler(GraspRuntimeException.class)
    public ResponseEntity handleException(GraspRuntimeException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ExceptionDTO("GraspRuntime threw Exception", e.getErrors()));
    }
}
