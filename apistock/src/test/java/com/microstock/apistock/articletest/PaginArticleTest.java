package com.microstock.apistock.articletest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;
import com.microstock.apistock.domain.util.PaginArticle;
import com.microstock.apistock.domain.util.article.ArticleDto;

class PaginArticleTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        List<ArticleDto> articles = Arrays.asList(
            new ArticleDto(1, "Article 1", "Description 1", 10.0, 5, "Brand 1", null),
            new ArticleDto(2, "Article 2", "Description 2", 15.0, 10, "Brand 2", null)
        );
        int currentPage = 1;
        int size = 10;
        int totalPages = 5;
        int totalData = 50;

        // Act
        PaginArticle paginArticle = new PaginArticle(articles, currentPage, size, totalPages, totalData);

        // Assert
        assertEquals(articles, paginArticle.getArticles());
        assertEquals(currentPage, paginArticle.getCurrentPage());
        assertEquals(size, paginArticle.getSize());
        assertEquals(totalPages, paginArticle.getTotalpages());
        assertEquals(totalData, paginArticle.getTotalData());
    }

    @Test
    void testSetters() {
        // Arrange
        List<ArticleDto> articles = Arrays.asList(
            new ArticleDto(1, "Article 1", "Description 1", 10.0, 5, "Brand 1", null),
            new ArticleDto(2, "Article 2", "Description 2", 15.0, 10, "Brand 2", null)
        );
        int currentPage = 1;
        int size = 10;
        int totalPages = 5;
        int totalData = 50;

        PaginArticle paginArticle = new PaginArticle(articles, currentPage, size, totalPages, totalData);

        // Act
        List<ArticleDto> newArticles = Arrays.asList(
            new ArticleDto(3, "Article 3", "Description 3", 20.0, 15, "Brand 3", null)
        );
        paginArticle.setArticles(newArticles);
        paginArticle.setCurrentPage(2);
        paginArticle.setSize(5);
        paginArticle.setTotalpages(3);
        paginArticle.setTotalData(15);

        // Assert
        assertEquals(newArticles, paginArticle.getArticles());
        assertEquals(2, paginArticle.getCurrentPage());
        assertEquals(5, paginArticle.getSize());
        assertEquals(3, paginArticle.getTotalpages());
        assertEquals(15, paginArticle.getTotalData());
    }

    @Test
    void testEmptyConstructor() {
        // Arrange
        PaginArticle paginArticle = new PaginArticle(null, null, null, null, null);

        // Act & Assert
        assertNull(paginArticle.getArticles());
        assertNull(paginArticle.getCurrentPage());
        assertNull(paginArticle.getSize());
        assertNull(paginArticle.getTotalpages());
        assertNull(paginArticle.getTotalData());
    }
}

