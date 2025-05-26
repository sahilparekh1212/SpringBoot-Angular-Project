package com.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.dto.ErrorResponse;
import com.backend.exceptions.SomeCustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SomeCustomException.class)
    public ResponseEntity<?> handleSomeCustomException(SomeCustomException ex) {
        ErrorResponse body = new ErrorResponse(ex.getMessage(),
                "Error for handleSomeCustomException");
        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<?> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {
        ErrorResponse body = new ErrorResponse(ex.getMessage(),
                "Error for ArrayIndexOutOfBoundsException");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
        ErrorResponse body = new ErrorResponse(ex.getMessage(),
                "Error for NullPointerException");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        ErrorResponse body = new ErrorResponse(ex.getMessage(),
                "Error for handleGenericException");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse body = new ErrorResponse(ex.getMessage(),
                "Error for HttpRequestMethodNotSupportedException");
        return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
    }

}
