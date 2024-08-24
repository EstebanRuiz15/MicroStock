package com.microstock.apistock.infraestructur.driving_http.mappers;

import org.mapstruct.Mapper;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.CategoryDtoAdd;

@Mapper(componentModel = "spring")
    public interface IMapperPeticionAdd {
    
        Category toCategoria(CategoryDtoAdd categoryDtoAgre);
    
    }