package com.microstock.apistock.infraestructura.salida.mapper;

import org.mapstruct.Mapper;

import com.microstock.apistock.dominio.modelo.Categoria;
import com.microstock.apistock.infraestructura.salida.entity.CategoriaEntity;

@Mapper(componentModel = "spring")
    public interface ICategoriaToEntitymapper {
    
        CategoriaEntity toCategoriaEntity(Categoria categoria);
    
    }
    