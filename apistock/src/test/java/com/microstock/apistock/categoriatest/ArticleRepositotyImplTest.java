package com.microstock.apistock.categoriatest;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driven_rp.adapter.CategoryRepositoryImpl;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;
import com.microstock.apistock.infraestructur.driven_rp.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.CategoriaRepositoryJpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class ArticleRepositotyImplTest {
    @Mock
    private CategoriaRepositoryJpa jpaRepository;

    @Mock
    private ICategoriaToEntitymapper mapperEntity;

    @InjectMocks
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private Category category = new Category(1, "Electronics","descr");
    private Category category2 = new Category(2, "Books","descr");
    private CategoryEntity categoryEntity = new CategoryEntity(1, "Electronics","descr");
    private CategoryEntity categoryEntity2 = new CategoryEntity(2, "Books","descr");

     @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testSaveCategory() {

        when(mapperEntity.toCategoriaEntity(category)).thenReturn(categoryEntity);

        // Act
        categoryRepositoryImpl.saveCategory(category);

        // Assert
        verify(jpaRepository).save(categoryEntity);
    }

    @Test
    void testFindByNombreIgnoreCase_Found() {
        // Arrange
        String categoryName = "Electronics";

        when(jpaRepository.findByNameIgnoreCase(categoryName)).thenReturn(Optional.of(category));
        when(mapperEntity.toCategory(categoryEntity)).thenReturn(category);

        // Act
        Optional<Category> result = categoryRepositoryImpl.findByNombreIgnoreCase(categoryName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void testFindByNombreIgnoreCase_NotFound() {
        // Arrange
        String categoryName = "NonExistent";

        when(jpaRepository.findByNameIgnoreCase(categoryName)).thenReturn(Optional.empty());

        // Act
        Optional<Category> result = categoryRepositoryImpl.findByNombreIgnoreCase(categoryName);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testFindByCategorias() {
        List<CategoryEntity> categoryEntities = Arrays.asList(categoryEntity, categoryEntity2);

        List<Category> categories = Arrays.asList(category, category2);

        when(jpaRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(categoryEntities);
        when(mapperEntity.toCategory(categoryEntity)).thenReturn(category);
        when(mapperEntity.toCategory(categoryEntity2)).thenReturn(category2);

        // Act
        List<Category> result = categoryRepositoryImpl.findByCategorias("asc");

        // Assert
        assertEquals(categories, result);
    }

    @Test
    void testFindById_Found() {
        // Arrange
        Integer categoryId = 1;
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity.setId(categoryId);
        categoryEntity.setName("Electronics");

        when(jpaRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity1));

        // Act
        Optional<CategoryEntity> result = categoryRepositoryImpl.findById(categoryId);

        // Assert
        assertTrue(result.isPresent(), "Expected CategoryEntity to be present");
        assertEquals(categoryEntity1, result.get(), "Expected CategoryEntity to match");
    }


    @Test
    void testFindById_NotFound() {
        // Arrange
        Integer categoryId = 1;

        when(jpaRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act
        Optional<CategoryEntity> result = categoryRepositoryImpl.findById(categoryId);

        // Assert
        assertFalse(result.isPresent());
    }
}
