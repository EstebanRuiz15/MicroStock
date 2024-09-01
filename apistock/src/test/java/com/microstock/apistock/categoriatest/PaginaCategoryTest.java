package com.microstock.apistock.categoriatest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.PaginCategory;

class PaginaCategoryTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        List<Category> categories = Arrays.asList(
            new Category(1, "Category 1","desc"),
            new Category(2, "Category 2","desc")
        );
        int currentPage = 1;
        int size = 10;
        int totalPages = 5;
        int totalData = 50;

        // Act
        PaginCategory paginCategory = new PaginCategory(categories, currentPage, size, totalPages, totalData);

        // Assert
        assertEquals(categories, paginCategory.getCategory());
        assertEquals(currentPage, paginCategory.getCurrentPage());
        assertEquals(size, paginCategory.getSize());
        assertEquals(totalPages, paginCategory.getTotalPages());
        assertEquals(totalData, paginCategory.getTotalData());
    }

    @Test
    void testSetters() {
        // Arrange
        List<Category> categories = Arrays.asList(
            new Category(1, "Category 1","descr"),
            new Category(2, "Category 2","descr")
        );
        int currentPage = 1;
        int size = 10;
        int totalPages = 5;
        int totalData = 50;

        PaginCategory paginCategory = new PaginCategory(categories, currentPage, size, totalPages, totalData);

        // Act
        List<Category> newCategories = Arrays.asList(
            new Category(3, "Category 3","descr")
        );
        paginCategory.setCategory(newCategories);
        paginCategory.setCurrentPage(2);
        paginCategory.setSize(5);
        paginCategory.setTotalPages(3);
        paginCategory.setTotalData(15);

        // Assert
        assertEquals(newCategories, paginCategory.getCategory());
        assertEquals(2, paginCategory.getCurrentPage());
        assertEquals(5, paginCategory.getSize());
        assertEquals(3, paginCategory.getTotalPages());
        assertEquals(15, paginCategory.getTotalData());
    }

    @Test
    void testEmptyConstructor() {
        // Arrange
        PaginCategory paginCategory = new PaginCategory(null, null, null, null, null);

        // Act & Assert
        assertNull(paginCategory.getCategory());
        assertNull(paginCategory.getCurrentPage());
        assertNull(paginCategory.getSize());
        assertNull(paginCategory.getTotalPages());
        assertNull(paginCategory.getTotalData());
    }
}
