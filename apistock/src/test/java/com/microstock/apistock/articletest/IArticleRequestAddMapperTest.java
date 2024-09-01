package com.microstock.apistock.articletest;

import com.microstock.apistock.domain.exception.ErrorException;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IBrandToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.ArticleDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IArticleRequestAddMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;

class IArticleRequestAddMapperTest {

    @InjectMocks
    private IArticleRequestAddMapper mapper = Mappers.getMapper(IArticleRequestAddMapper.class);

    @Mock
    private ICategoryService categoryService;

    @Mock
    private IBrandService brandService;

    @Mock
    private ICategoriaToEntitymapper categoryMapper;

    @Mock
    private IBrandToEntityMapper brandMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToArticleModel_Success() {
        // Arrange
        ArticleDtoAdd dto = new ArticleDtoAdd("Article 1", "Description 1", 100.0, 10, 1, Arrays.asList(1, 2));
        
        BrandEntity brandEnti = new BrandEntity(1, "Brand 1", "descri");
        Brand brand = new Brand(1, "Brand 1","descri");
        CategoryEntity category1Enti = new CategoryEntity(1, "Category 1", "descri1");
        Category category1 = new Category(1, "Category 1", "descri1");
        CategoryEntity category2Enti = new CategoryEntity(2, "Category 2","descri");
        Category category2 = new Category(1, "Category 1", "descri1");

        when(brandService.findById(1)).thenReturn(Optional.of(brandEnti));
        when(brandMapper.toBrand(any())).thenReturn(brand);
        
        when(categoryService.findById(1)).thenReturn(Optional.of(category1Enti));
        when(categoryService.findById(2)).thenReturn(Optional.of(category2Enti));
        when(categoryMapper.toCategory(any())).thenReturn(category1, category2);

        // Act
        Article article = mapper.toArticleModel(dto, categoryService, brandService, brandMapper, categoryMapper);

        // Assert
        assertNotNull(article);
        assertEquals(dto.getName(), article.getName());
        assertEquals(dto.getDescription(), article.getDescription());
        assertEquals(brand, article.getBrand());
        assertEquals(2, article.getCategories().size());
    }

    @Test
    void testToArticleModel_BrandNotFound() {
        // Arrange
        ArticleDtoAdd dto = new ArticleDtoAdd("Article 1", "Description 1", 100.0, 10, 99, Arrays.asList(1, 2));

        when(brandService.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () ->
            mapper.toArticleModel(dto, categoryService, brandService, brandMapper, categoryMapper)
        );

        assertTrue(exception.getMessage().contains(ConstantsDomain.NO_BRAND_EXCEPTION_MESSAGE));
    }

    @Test
    void testToArticleModel_CategoryNotFound() {
        // Arrange
        ArticleDtoAdd dto = new ArticleDtoAdd("Article 1", "Description 1", 100.0, 10, 1, Arrays.asList(99));

        BrandEntity brandEnti = new BrandEntity(1, "Brand 1","descri");

        when(brandService.findById(1)).thenReturn(Optional.of(brandEnti));
        when(categoryService.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () ->
            mapper.toArticleModel(dto, categoryService, brandService, brandMapper, categoryMapper)
        );

        assertTrue(exception.getMessage().contains(ConstantsDomain.NO_BRAND_EXCEPTION_MESSAGE));
    }

    @Test
    void testToArticleModel_NullBrandId() {
        // Arrange
        ArticleDtoAdd dto = new ArticleDtoAdd("Article 1", "Description 1", 100.0, 10, null, Arrays.asList(1, 2));

        CategoryEntity category1Enti = new CategoryEntity(1, "Category 1","descri");
        CategoryEntity category2Enti= new CategoryEntity(2, "Category 2","descri");
        Category category1 = new Category(1, "Category 1","descri");
        Category category2 = new Category(2, "Category 2","descri");


        when(categoryService.findById(1)).thenReturn(Optional.of(category1Enti));
        when(categoryService.findById(2)).thenReturn(Optional.of(category2Enti));
        when(categoryMapper.toCategory(any())).thenReturn(category1, category2);

        // Act
        Article article = mapper.toArticleModel(dto, categoryService, brandService, brandMapper, categoryMapper);

        // Assert
        assertNotNull(article);
        assertNull(article.getBrand());  // Brand should be null if brandId is null
        assertEquals(2, article.getCategories().size());
    }

    @Test
    void testToArticleModel_NullCategories() {
        // Arrange
        ArticleDtoAdd dto = new ArticleDtoAdd("Article 1", "Description 1", 100.0, 10, 1, null);

        BrandEntity brandEnti = new BrandEntity(1, "Brand 1","descri");
        Brand brand = new Brand(1, "Brand 1","descri");

        when(brandService.findById(1)).thenReturn(Optional.of(brandEnti));
        when(brandMapper.toBrand(any())).thenReturn(brand);

        // Act
        Article article = mapper.toArticleModel(dto, categoryService, brandService, brandMapper, categoryMapper);

        // Assert
        assertNotNull(article);
        assertEquals(brand, article.getBrand());
        assertNull(article.getCategories());  // Categories should be null if categoriesId is null
    }
}

