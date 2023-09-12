package com.system.movietheater.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlingErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

//    @ExceptionHandler(RuntimeException.class)

    public record DataValidationError(String field, String message){
        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
