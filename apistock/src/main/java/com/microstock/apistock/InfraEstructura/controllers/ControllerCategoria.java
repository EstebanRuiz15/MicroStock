package com.microstock.apistock.infraestructura.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microstock.apistock.aplicacion.dtos.CategoriaDto;
import com.microstock.apistock.aplicacion.servicios.ServicioCategoria;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/Api/Stock")
public class ControllerCategoria {
    
    private final ServicioCategoria servipost;

    public ControllerCategoria(ServicioCategoria servipost) {
        this.servipost = servipost;
    }

    @Operation(summary = "Método para crear categorías", 
    description = "Este método permite crear una nueva categoría proporcionando los datos necesarios en el cuerpo de la solicitud.")

    @PostMapping("/agregar")
    public ResponseEntity<CategoriaDto> crearCategoria(@RequestBody CategoriaDto categoria) {
        
      try {
            CategoriaDto resultado = servipost.crearCat(categoria);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            // Manejar la excepción y devolver un estado de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}