package com.microstock.apistock.infraestructur.driven_rp.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microstock.apistock.domain.interfaces.ICategoryRepositoryPort;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driven_rp.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.CategoriaRepositoryJpa;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements ICategoryRepositoryPort{
    private final CategoriaRepositoryJpa jpaRepository;
    private final ICategoriaToEntitymapper mapperEntity;
    @Override
    public void saveCategory(Category category) {
         jpaRepository.save(mapperEntity.toCategoriaEntity(category));
    }

    @Override
    public Optional<Category> findByNombreIgnoreCase(String name) {
        return jpaRepository.findByNameIgnoreCase(name);
    }

    
}
