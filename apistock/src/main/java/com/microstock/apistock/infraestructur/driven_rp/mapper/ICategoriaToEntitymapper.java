package com.microstock.apistock.infraestructur.driven_rp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;

@Mapper(componentModel = "spring")
    public interface ICategoriaToEntitymapper {
    
        CategoryEntity toCategoriaEntity(Category category);
        Category toCategory(CategoryEntity categoryEntity);
    
    }
    