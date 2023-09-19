package com.system.movietheater.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandlingErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleError404(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundExcpetion.class)
    public ResponseEntity<String> handleErrorUserNotFound404(UserNotFoundExcpetion exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataValidationError>> handleError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleErrorBadRequest400(BadRequestException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    public record DataValidationError(String field, String message){
        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
