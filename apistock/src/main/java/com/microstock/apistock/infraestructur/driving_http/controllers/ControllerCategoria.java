package com.microstock.apistock.infraestructur.driving_http.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.CategoryDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IMapperPeticionAdd;
import com.microstock.apistock.infraestructur.util.ConstantsInfraestructure;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/Api/Stock")
@Service 
public class ControllerCategoria {
    
    private final ICategoryService serviceCategoria;
    private final IMapperPeticionAdd mapperadd;

    public ControllerCategoria(ICategoryService serviceCategoria, IMapperPeticionAdd mapperadd) {
        this.serviceCategoria= serviceCategoria;
        this.mapperadd=mapperadd;
    }

    @Operation(summary = "Method for creating categories", 
    description = "This method allows you to create a new category by providing the necessary data in the body of the request.\r\n" + //
                "rules: - the name must be unique\r\n" + //
                "       - the name cannot be longer than 50 characters\r\n" + //
                "       - the description cannot be longer than 90 characters")

    @PostMapping("/add")
    public ResponseEntity<Void> crearCategoria(@RequestBody CategoryDtoAdd category) {
        try {
            serviceCategoria.createCategory(mapperadd.toCategoria(category));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ConstantsInfraestructure.ERROR_CATEGORY, e);
        }
    }
}