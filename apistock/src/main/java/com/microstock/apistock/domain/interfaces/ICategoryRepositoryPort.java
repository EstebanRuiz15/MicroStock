package com.microstock.apistock.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;

public interface ICategoryRepositoryPort {
    void saveCategory(Category category);
    Optional<Category> findByNombreIgnoreCase(String nombre);
    List<Category> findByCategorias(String orden);
    Optional<CategoryEntity> findById(Integer id);
    
}