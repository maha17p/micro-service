package com.micro_service.patient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("statusCode",HttpStatus.NOT_FOUND.value());
        return  new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errors.put("statusCode",HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
        Map<String,Object> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        errors.put("statusCode",HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleBadRequest(BadRequestException ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errors.put("statusCode",HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String,Object>> handleUnauthorized(UnauthorizedException ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errors.put("statusCode",HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errors,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleAllUnhandled(Exception ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errors.put("statusCode",HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String,Object>> handleRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("message", "Method not allowed: " + ex.getMethod());
        error.put("statusCode", HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }


}
