package com.microstock.apistock.articletest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.microstock.apistock.domain.exception.ErrorException;
import com.microstock.apistock.domain.exception.ErrorExceptionParam;
import com.microstock.apistock.domain.interfaces.IArticleRepositoryPort;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.services.ServiceArticleImpl;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.domain.util.PaginArticle;

@SpringBootTest
 class ArticleServiceTest {
    

    @Mock
    private IArticleRepositoryPort repository;

    @InjectMocks
    private ServiceArticleImpl service;

     Brand brand = new Brand(1, "Brand Name","descrip");
     Category category1 = new Category(1, "Category 1","descrip");
     Category category2 = new Category(2, "Category 2","descrip");

    @BeforeEach
    public void setUp() {
     repository=mock(IArticleRepositoryPort.class);
     service = new ServiceArticleImpl(repository);
    }

    @Test
     void testCreateArticle_WhenArticleDoesNotExist_ShouldSaveArticle() {
        // Arrange
        Article article = new Article(1, "Article Name", "Description", 100.0, 10, brand, List.of(category1,category2));
        when(repository.findByNombreIgnoreCase(anyString())).thenReturn(Optional.empty());

        // Act
        service.createArticle(article);

        // Assert
        verify(repository, times(1)).findByNombreIgnoreCase(article.getName().trim());
        verify(repository, times(1)).saveArticle(article);
    }

    @Test
     void testCreateArticle_WhenArticleExists_ShouldThrowErrorException() {
        // Arrange
        Article article = new Article(1, "Article Name", "Description", 100.0, 10, brand, List.of(category1));
        when(repository.findByNombreIgnoreCase(anyString())).thenReturn(Optional.of(article));

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () -> service.createArticle(article));
        assertEquals(ConstantsDomain.NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage());
        verify(repository, times(1)).findByNombreIgnoreCase(article.getName().trim());
        verify(repository, never()).saveArticle(article);
    }

    @Test
    void testGetAllArticles_Success() {
        List<Article> articles = Arrays.asList(
            new Article(1, "Samsung TV", "Smart TV", 500.0, 10, brand, Arrays.asList(category1, category2)),
            new Article(2, "Nike Shoes", "Running Shoes", 150.0, 20, brand, Arrays.asList(category2,category1))
        );

        when(repository.finByArticle()).thenReturn(articles);

        PaginArticle result = service.getAllArticles(0, 10, "asc", "article");

        assertNotNull(result);
        assertEquals(2, result.getTotalData());
        assertEquals(1, result.getTotalpages());
        assertEquals(2, result.getArticles().size());
        assertEquals("Nike Shoes", result.getArticles().get(0).getName());
    }

     @Test
    void testGetAllArticles_InvalidOrderParam() {
        ErrorExceptionParam exception = assertThrows(ErrorExceptionParam.class, () ->
            service.getAllArticles(0, 10, "invalid", "article")
        );
        assertEquals(ConstantsDomain.ORDEN_DIFERENT_ASC_OR_DESC_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetAllArticles_InvalidPageParam() {
        ErrorExceptionParam exception = assertThrows(ErrorExceptionParam.class, () ->
            service.getAllArticles(-1, 10, "asc", "article")
        );
        assertEquals(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetAllArticles_InvalidSizeParam() {
        ErrorExceptionParam exception = assertThrows(ErrorExceptionParam.class, () ->
            service.getAllArticles(0, 0, "asc", "article")
        );
        assertEquals(ConstantsDomain.SIZE_MIN_CHARACTER_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetAllArticles_NoDataFound() {
        when(repository.finByArticle()).thenReturn(Arrays.asList());

        ErrorException exception = assertThrows(ErrorException.class, () ->
            service.getAllArticles(0, 10, "asc", "article")
        );
        assertEquals(ConstantsDomain.NO_DATA_ARTICLE_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetAllArticles_OrderDescending() {
        List<Article> articles = Arrays.asList(
            new Article(1, "Samsung TV", "Smart TV", 500.0, 10, brand, Arrays.asList(category1,category2)),
            new Article(2, "Nike Shoes", "Running Shoes", 150.0,25, brand, Arrays.asList(category2,category1))
        );

        when(repository.finByArticle()).thenReturn(articles);

        PaginArticle result = service.getAllArticles(0, 10, "desc", "article");

        assertNotNull(result);
        assertEquals("Nike Shoes", result.getArticles().get(1).getName());
        assertEquals("Samsung TV", result.getArticles().get(0).getName());
    }

    
}
