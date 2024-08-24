package com.microstock.apistock.domain.interfaces;

import java.util.Optional;

import com.microstock.apistock.domain.model.Category;

public interface ICategoryRepositoryPort {
    void saveCategory(Category category);
    Optional<Category> findByNombreIgnoreCase(String nombre);
    
}