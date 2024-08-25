package com.microstock.apistock.infraestructur.driven_rp.adapter;

import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes.Name;
import java.util.stream.Collectors;

import com.microstock.apistock.domain.interfaces.ICategoryRepositoryPort;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;
import com.microstock.apistock.infraestructur.driven_rp.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.CategoriaRepositoryJpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

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

    @Override
    public List<Category> findByCategorias(String orden) {
        Sort.Direction direction = Sort.Direction.fromString(orden.toUpperCase());
        Sort sort = Sort.by(direction,"name");
        List<CategoryEntity> categoriaEntities=jpaRepository.findAll(sort);
            
        return categoriaEntities.stream()
                .map(mapperEntity::toCategory)
                .collect(Collectors.toList());
    }

    
}
