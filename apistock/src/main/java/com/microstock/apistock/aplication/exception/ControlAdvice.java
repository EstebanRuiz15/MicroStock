package com.microstock.apistock.aplication.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorException;
import com.microstock.apistock.domain.util.ConstantsDomain;

@ControllerAdvice
public class ControlAdvice {
    
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<?> resourceNotFoundException(ErrorException ex, WebRequest request) {
        ExceptionResponse errorDetails = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
       
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(), 
                ConstantsDomain.ERROR_VALIDATION, 
                 errors.toString() 
        );
    
        // Devolver el objeto ExceptionResponse en la respuesta
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
