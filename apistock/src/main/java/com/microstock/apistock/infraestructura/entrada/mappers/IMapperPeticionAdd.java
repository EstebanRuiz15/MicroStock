package com.microstock.apistock.infraestructura.entrada.mappers;

import org.mapstruct.Mapper;

import com.microstock.apistock.dominio.modelo.Categoria;
import com.microstock.apistock.infraestructura.entrada.dtos.peticion.CategoriaDtoAgre;

@Mapper(componentModel = "spring")
    public interface IMapperPeticionAdd {
    
        Categoria toCategoria(CategoriaDtoAgre categoriaDtoAgre);
    
    }