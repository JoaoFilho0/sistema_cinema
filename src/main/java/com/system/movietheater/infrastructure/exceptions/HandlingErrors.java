package com.system.movietheater.infrastructure.exceptions;

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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleErrorUserNotFound404(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

    @ExceptionHandler(MovieTheaterNotFoundException.class)
    public ResponseEntity<String> handleErrorMovieTheaterNotFound404(MovieTheaterNotFoundException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

    @ExceptionHandler(HoraryNotFoundException.class)
    public ResponseEntity<String> handleErrorHoraryNotFound404(HoraryNotFoundException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<String> handleErrorSessionNotFound404(SessionNotFoundException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<String> handleErrorRoomNotFound404(RoomNotFoundException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(exception.getMessage());
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleErrorMovieNotFound404(MovieNotFoundException exception) {
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

    @ExceptionHandler(HoraryAlreadyExistsException.class)
    public ResponseEntity<String> handleErrorHoraryAlreadyExists400(HoraryAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(HoraryInvalidException.class)
    public ResponseEntity<String> handleErrorSessionHoraryInvalid400(HoraryInvalidException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(SessionDayInvalidException.class)
    public ResponseEntity<String> handleErrorSessionDayInvalid400(SessionDayInvalidException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(SessionDateInvalidException.class)
    public ResponseEntity<String> handleErrorSessionDateInvalid400(SessionDateInvalidException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(SessionDurationConflictException.class)
    public ResponseEntity<String> handleErrorSessionDurationConflict400(SessionDurationConflictException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(SessionDurationInvalidException.class)
    public ResponseEntity<String> handleErrorSessionDurationConflict400(SessionDurationInvalidException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<String> handleErrorMovieAlreadyExists400(MovieAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<String> handleErrorEmptyField400(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handleErrorAddressNotFound400(AddressNotFoundException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    public record DataValidationError(String field, String message){

        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
