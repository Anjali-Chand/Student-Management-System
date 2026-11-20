
package com.student.studentmanagement.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleStudentNotFound(
	        StudentNotFoundException ex) {

	    Map<String, String> error = Map.of(
	            "message", ex.getMessage()
	    );

	    return new ResponseEntity<>(
	            error,
	            HttpStatus.NOT_FOUND
	    );
	
    }
	

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidationErrors(
        MethodArgumentNotValidException ex) {

    Map<String, String> errors = new LinkedHashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );

    return new ResponseEntity<>(
            errors,
            HttpStatus.BAD_REQUEST
    );
  }
}
