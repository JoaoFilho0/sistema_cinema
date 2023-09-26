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

    @ExceptionHandler(TokenInvalidOrExpiredException.class)
    public ResponseEntity<String> handleErrorTokenInvalidOrExpired401(TokenInvalidOrExpiredException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(exception.getMessage());
    }

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

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<String> handleErrorEmailAlreadyExists400(EmailAlreadyRegisteredException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(NameMovieTheaterAlreadyRegisteredException.class)
    public ResponseEntity<String> handleErrorNameMovieTheaterAlreadyExists400(NameMovieTheaterAlreadyRegisteredException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AddressMovieTheaterAlreadyExistsException.class)
    public ResponseEntity<String> handleErrorAddressMovieTheaterAlreadyExists400(AddressMovieTheaterAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<String> handleErrorRoomAlreadyExists400(RoomAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    public record DataValidationError(String field, String message){

        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
