package com.microstock.apistock.InfraEstructura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microstock.apistock.Aplicacion.Dto.CategoriaDto;
import com.microstock.apistock.Aplicacion.mapper.MapperCategoria;
import com.microstock.apistock.Aplicacion.servicios.ServicioPostCategoria;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/Api/Stock")
public class ControllerCategoria {
    
    private final ServicioPostCategoria servipost;
    
    // comentario de prueba
    public ControllerCategoria(ServicioPostCategoria servipost) {
        this.servipost = servipost;
    }


    @PostMapping("/agregar")
    private CategoriaDto crearCategoria(@RequestBody @Valid CategoriaDto categoria) {
        return servipost.crearCat(categoria);
    }
}