package com.microstock.apistock.aplicacion.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.microstock.apistock.dominio.excepciones.excepciones_categoria.ErroresCategoria;

@ControllerAdvice
public class ControlAdvice {
    
    @ExceptionHandler(ErroresCategoria.class)
    public ResponseEntity<?> resourceNotFoundException(ErroresCategoria ex, WebRequest request) {
        ExcepcionRespuesta errorDetails = new ExcepcionRespuesta(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
