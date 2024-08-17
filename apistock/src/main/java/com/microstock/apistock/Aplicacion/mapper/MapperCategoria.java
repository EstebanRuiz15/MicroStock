package com.microstock.apistock.Aplicacion.mapper;

import org.mapstruct.Mapper;

import com.microstock.apistock.Aplicacion.Dto.CategoriaDto;
import com.microstock.apistock.Dominio.Entity.Categoria;



@Mapper(componentModel = "spring")
public interface MapperCategoria {

    CategoriaDto CategoriatoToCategoriaDto(Categoria categoria);

    Categoria CategoriaDtoToCategoria(CategoriaDto categoriaDo);

}
