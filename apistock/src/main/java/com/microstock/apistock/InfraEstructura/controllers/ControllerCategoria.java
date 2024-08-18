package com.microstock.apistock.infraestructura.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microstock.apistock.aplicacion.dtos.CategoriaDto;
import com.microstock.apistock.aplicacion.servicios.ServicioPostCategoria;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/Api/Stock")
public class ControllerCategoria {
    
    private final ServicioPostCategoria servipost;

    public ControllerCategoria(ServicioPostCategoria servipost) {
        this.servipost = servipost;
    }

    @Operation(summary = "Método para crear categorías", 
    description = "Este método permite crear una nueva categoría proporcionando los datos necesarios en el cuerpo de la solicitud.")

    @PostMapping("/agregar")
    public CategoriaDto crearCategoria(@RequestBody CategoriaDto categoria) {
        
        return servipost.crearCat(categoria);
    }
}