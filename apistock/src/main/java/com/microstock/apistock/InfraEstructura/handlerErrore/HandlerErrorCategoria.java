package com.microstock.apistock.InfraEstructura.handlerErrore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.microstock.apistock.Aplicacion.Excepciones.ExcepcionesCategoria.DetailsErrorCategoria;
import com.microstock.apistock.Aplicacion.Excepciones.ExcepcionesCategoria.ErroresCategoria;

@ControllerAdvice
public class HandlerErrorCategoria {
    
    @ExceptionHandler(ErroresCategoria.class)
    public ResponseEntity<?> resourceNotFoundException(ErroresCategoria ex, WebRequest request) {
        DetailsErrorCategoria errorDetails = new DetailsErrorCategoria(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
