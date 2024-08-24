package com.microstock.apistock.aplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorCategory;

@ControllerAdvice
public class ControlAdvice {
    
    @ExceptionHandler(ErrorCategory.class)
    public ResponseEntity<?> resourceNotFoundException(ErrorCategory ex, WebRequest request) {
        ExceptionResponse errorDetails = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}