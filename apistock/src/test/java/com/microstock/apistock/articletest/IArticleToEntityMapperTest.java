package com.microstock.apistock.articletest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorException;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IArticleToEntityMapper;

@SpringBootTest
class IArticleToEntityMapperTest {

    @Mock
    private ICategoryService iCategoryService;

    @Mock
    private IBrandService iBrandService;

    @InjectMocks
    private IArticleToEntityMapper mapper = Mappers.getMapper(IArticleToEntityMapper.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toArticleEntity_ShouldMapFieldsCorrectly() {
        // Arrange
        Article article = new Article(1, "Article Name", "Description", 99.99, 10, 1, Arrays.asList(1, 2));
        BrandEntity brandEntity = new BrandEntity(1, "Brand Name","descri");
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Category 1","dexdsc");
        CategoryEntity categoryEntity2 = new CategoryEntity(2, "Category 2","descrip");

        when(iBrandService.findById(1)).thenReturn(Optional.of(brandEntity));
        when(iCategoryService.findById(1)).thenReturn(Optional.of(categoryEntity1));
        when(iCategoryService.findById(2)).thenReturn(Optional.of(categoryEntity2));

        // Act
        ArticleEntity articleEntity = mapper.toArticleEntity(article, iCategoryService, iBrandService);

        // Assert
        assertEquals(article.getName(), articleEntity.getName());
        assertEquals(article.getDescription(), articleEntity.getDescription());
        assertEquals(article.getPrice(), articleEntity.getPrice());
        assertEquals(article.getQuantity(), articleEntity.getQuantity());
        assertEquals(brandEntity, articleEntity.getBrand());
        assertEquals(Arrays.asList(categoryEntity1, categoryEntity2), articleEntity.getCategories());
    }

    @Test
    void toArticleEntity_ShouldThrowException_WhenBrandNotFound() {
        // Arrange
        Article article = new Article(1, "Article Name", "Description", 99.99, 10, 1, Arrays.asList(1, 2));

        when(iBrandService.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () ->
            mapper.toArticleEntity(article, iCategoryService, iBrandService)
        );
        assertEquals(ConstantsDomain.NO_BRAND_EXCEPTION_MESSAGE + ", " + 1, exception.getMessage());
    }

    @Test
    void toArticleEntity_ShouldThrowException_WhenCategoryNotFound() {
        // Arrange
        Article article = new Article(1, "Article Name", "Description", 99.99, 10, 1, Arrays.asList(1, 2));
        BrandEntity brandEntity = new BrandEntity(1, "Brand Name","new");

        when(iBrandService.findById(1)).thenReturn(Optional.of(brandEntity));
        when(iCategoryService.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () ->
            mapper.toArticleEntity(article, iCategoryService, iBrandService)
        );
        assertEquals(ConstantsDomain.NO_CATEGORY_EXCEPTION_MESSAGE + ", " + 1, exception.getMessage());
    }
}
