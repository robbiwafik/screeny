package com.company.screeny.advice;

import com.company.screeny.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ObjectControllerAdvice {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> handleNotFoundException(ObjectNotFoundException exc) {
        var errors = new HashMap<String, Object>();
        errors.put("detail", exc.getMessage());

        return new ResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> handleBadRequestException(
            MethodArgumentNotValidException exc
    ) {
        var result = exc.getBindingResult();
        var fieldErrors = result.getFieldErrors();
        var errors = new HashMap<String, String>();
        fieldErrors.forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HashMap<String, String>> handleBadRequestException(
            IllegalArgumentException exc
    ) {
        var errors = new HashMap<String, String>();
        errors.put("detail", exc.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
