package com.microstock.apistock.articletest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driven_rp.adapter.ArticleRepositoryImpl;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IArticleToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.ArticleRespositoryJpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
 class ArticleRepositotyImplTest {
    @Mock
    private ArticleRespositoryJpa respositoryJpa;

    @Mock
    private IArticleToEntityMapper mapper;

    @InjectMocks
    private ArticleRepositoryImpl articleRepository;
    
    private Category categori1=new Category(1,"name1","descr");
    private Category categori2=new Category(2,"name2","descr");
    private Brand brand=new Brand(1,"name","descri");
    @BeforeEach
    void setUp() {
        respositoryJpa=mock(ArticleRespositoryJpa.class);
        mapper=mock(IArticleToEntityMapper.class);
        articleRepository= new ArticleRepositoryImpl(respositoryJpa, mapper);
    }

    @Test
    void testSaveArticle() {

        
        Article article = new Article(1, "Test Article", "Test Description", 10.0, 5, brand, Arrays.asList(categori1,categori2));
        ArticleEntity articleEntity = new ArticleEntity();
        when(mapper.toArticleEntity(article)).thenReturn(articleEntity);

        // Act
        articleRepository.saveArticle(article);

        // Assert
        verify(respositoryJpa, times(1)).save(articleEntity);
    }

    @Test
    void testFindByNombreIgnoreCase() {
        // Arrange
        String articleName = "Test Article";
        ArticleEntity articleEntityOp = new ArticleEntity(); 
        Optional<ArticleEntity> articleEntity = Optional.of(articleEntityOp);
        Article article = new Article(1, "Test Article", "Test Description", 10.0, 5, brand, Arrays.asList(categori1,categori2));
        when(respositoryJpa.findByNameIgnoreCase(articleName)).thenReturn(articleEntity);
        when(mapper.toArticle(articleEntity)).thenReturn(Optional.of(article));

        // Act
        Optional<Article> result = articleRepository.findByNombreIgnoreCase(articleName);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(article, result.get());
    }

    @Test
    void testFindByNombreIgnoreCase_NotFound() {
        // Arrange
        String articleName = "Nonexistent Article";
        when(respositoryJpa.findByNameIgnoreCase(articleName)).thenReturn(null);

        // Act
        Optional<Article> result = articleRepository.findByNombreIgnoreCase(articleName);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testFinByArticle() {
        // Arrange
        ArticleEntity articleEntity1 = new ArticleEntity();
        ArticleEntity articleEntity2 = new ArticleEntity();
        List<ArticleEntity> articleEntities = Arrays.asList(articleEntity1, articleEntity2);

        Article article1 = new Article(1, "Test Article 1", "Test Description 1", 10.0, 5, brand, Arrays.asList(categori1,categori2));
        Article article2 = new Article(2, "Test Article 2", "Test Description 2", 20.0, 10, brand, Arrays.asList(categori2,categori1));
        when(respositoryJpa.findAll()).thenReturn(articleEntities);
        when(mapper.toListArticle(articleEntities)).thenReturn(Arrays.asList(article1, article2));

        // Act
        List<Article> result = articleRepository.finByArticle();

        // Assert
        assertEquals(2, result.size());
        assertEquals(article1, result.get(0));
        assertEquals(article2, result.get(1));
    }
}
