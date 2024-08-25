package com.microstock.apistock.infraestructur.driven_rp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoriaRepositoryJpa extends JpaRepository<CategoryEntity, Integer> {
    Optional<Category> findByNameIgnoreCase(String name);
    List<CategoryEntity> findAll(Sort sort);

}