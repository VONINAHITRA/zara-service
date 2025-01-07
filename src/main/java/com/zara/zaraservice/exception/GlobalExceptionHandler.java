package com.zara.zaraservice.exception;

import com.zara.zaraservice.exception.customexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handling for Not Found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> status = new HashMap<>();
        status.put("httpStatus", HttpStatus.NOT_FOUND.name());
        status.put("message", ex.getMessage());
        status.put("statusCode", HttpStatus.NOT_FOUND.value());

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Handling for DataIntegrityViolationException
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        Map<String, Object> status = new HashMap<>();
        status.put("httpStatus", HttpStatus.CONFLICT.name());
        status.put("message", ex.getMessage());
        status.put("statusCode", HttpStatus.CONFLICT.value());

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Handling for RoleNotNullException
    @ExceptionHandler(RoleNotNullException.class)
    public ResponseEntity<Map<String, Object>> handleRoleNotNullException(RoleNotNullException ex) {
        Map<String, Object> status = new HashMap<>();
        status.put("httpStatus", HttpStatus.BAD_REQUEST.name());
        status.put("message", ex.getMessage());
        status.put("statusCode", HttpStatus.BAD_REQUEST.value());
        status.put("exceptionType", ex.getClass().getSimpleName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Handling for InvalidRefreshTokenException
    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRefreshTokenException(InvalidRefreshTokenException ex) {
        Map<String, Object> status = new HashMap<>();
        status.put("httpStatus", HttpStatus.UNAUTHORIZED.name());
        status.put("message", ex.getMessage());
        status.put("statusCode", HttpStatus.UNAUTHORIZED.value());
        status.put("exceptionType", ex.getClass().getSimpleName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Handling for UserInactiveException
    @ExceptionHandler(UserInactiveException.class)
    public ResponseEntity<Map<String, Object>> handleUserInactiveException(UserInactiveException ex) {
        Map<String, Object> status = new HashMap<>();
        status.put("httpStatus", HttpStatus.BAD_REQUEST.name());
        status.put("message", ex.getMessage());
        status.put("statusCode", HttpStatus.BAD_REQUEST.value());
        status.put("exceptionType", ex.getClass().getSimpleName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Handling for Invalid Date Exception
    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<Map<String, Object>>handleDateInvalidException(InvalidDateRangeException ex) {
        Map<String, Object> status = new HashMap<>();
        status.put("httpStatus", HttpStatus.BAD_REQUEST.name());
        status.put("message", ex.getMessage());
        status.put("statusCode", HttpStatus.BAD_REQUEST.value());
        status.put("exceptionType", ex.getClass().getSimpleName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Handling for Invalid Date Exception
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String, Object>>handleDataInvalidException(InvalidDataException ex) {
        Map<String, Object> status = new HashMap<>();
        status.put("httpStatus", HttpStatus.BAD_REQUEST.name());
        status.put("message", ex.getMessage());
        status.put("statusCode", HttpStatus.BAD_REQUEST.value());
        status.put("exceptionType", ex.getClass().getSimpleName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
