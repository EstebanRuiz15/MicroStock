package com.microstock.apistock.infraestructura.entrada.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.microstock.apistock.dominio.interfaces.ICategoriaService;
import com.microstock.apistock.infraestructura.entrada.dtos.peticion.CategoriaDtoAgre;
import com.microstock.apistock.infraestructura.entrada.mappers.IMapperPeticionAdd;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/Api/Stock")
public class ControllerCategoria {
    
    private final ICategoriaService serviceCategoria;
    private final IMapperPeticionAdd mapperadd;

    public ControllerCategoria(ICategoriaService serviceCategoria, IMapperPeticionAdd mapperadd) {
        this.serviceCategoria= serviceCategoria;
        this.mapperadd=mapperadd;
    }

    @Operation(summary = "Método para crear categorías", 
    description = "Este método permite crear una nueva categoría proporcionando los datos necesarios en el cuerpo de la solicitud.")

    @PostMapping("/agregar")
    public ResponseEntity<Void> crearCategoria(@RequestBody CategoriaDtoAgre categoria) {
        try {
            serviceCategoria.crearCategoria(mapperadd.toCategoria(categoria));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear la categoría", e);
        }
    }
}