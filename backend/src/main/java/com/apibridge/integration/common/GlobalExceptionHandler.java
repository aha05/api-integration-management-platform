package com.apibridge.integration.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ){
        var errors = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach( error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                         HttpServletRequest request) {
        ErrorResponse errorResponseDTO = new ErrorResponse(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException exception,
                                                                         HttpServletRequest request) {
        ErrorResponse errorResponseDTO = new ErrorResponse(
                request.getRequestURI(),
                HttpStatus.CONFLICT,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizeAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizeAccessException(UnauthorizeAccessException exception,
                                                                             HttpServletRequest request) {
        ErrorResponse errorResponseDTO = new ErrorResponse(
                request.getRequestURI(),
                HttpStatus.UNAUTHORIZED,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

}
