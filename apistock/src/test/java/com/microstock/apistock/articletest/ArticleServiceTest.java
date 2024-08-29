package com.microstock.apistock.articletest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorException;
import com.microstock.apistock.domain.interfaces.IArticleRepositoryPort;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.services.ServiceArticleImpl;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;

@SpringBootTest
 class ArticleServiceTest {
    

    @Mock
    private IArticleRepositoryPort repository;

    @InjectMocks
    private ServiceArticleImpl service;

    @BeforeEach
    public void setUp() {
     repository=mock(IArticleRepositoryPort.class);
     service = new ServiceArticleImpl(repository);

    }

    @Test
     void testCreateArticle_WhenArticleDoesNotExist_ShouldSaveArticle() {
        // Arrange
        Article article = new Article(1, "Article Name", "Description", 100.0, 10, 1, List.of(1, 2));
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
        Article article = new Article(1, "Article Name", "Description", 100.0, 10, 1, List.of(1, 2));
        when(repository.findByNombreIgnoreCase(anyString())).thenReturn(Optional.of(new ArticleEntity()));

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () -> service.createArticle(article));
        assertEquals(ConstantsDomain.NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage());
        verify(repository, times(1)).findByNombreIgnoreCase(article.getName().trim());
        verify(repository, never()).saveArticle(article);
    }
}
