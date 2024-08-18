package com.microstock.apistock.aplicacion.mappers;

import org.mapstruct.Mapper;

import com.microstock.apistock.aplicacion.dtos.CategoriaDto;
import com.microstock.apistock.dominio.entity.Categoria;



@Mapper(componentModel = "spring")
public interface MapperCategoria {

    CategoriaDto CategoriatoToCategoriaDto(Categoria categoria);

    Categoria CategoriaDtoToCategoria(CategoriaDto categoriaDo);

}
