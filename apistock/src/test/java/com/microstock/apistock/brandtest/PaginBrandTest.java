package com.microstock.apistock.brandtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.util.PaginBrand;

class PaginBrandTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        List<Brand> brands = Arrays.asList(
            new Brand(1, "Brand 1","desc"),
            new Brand(2, "Brand 2","desc")
        );
        int currentPage = 1;
        int size = 10;
        int totalPages = 5;
        int totalData = 50;

        // Act
        PaginBrand paginBrand = new PaginBrand(brands, currentPage, size, totalPages, totalData);

        // Assert
        assertEquals(brands, paginBrand.getBrands());
        assertEquals(currentPage, paginBrand.getCurrentPage());
        assertEquals(size, paginBrand.getSize());
        assertEquals(totalPages, paginBrand.getTotalPages());
        assertEquals(totalData, paginBrand.getTotalData());
    }

    @Test
    void testSetters() {
        // Arrange
        List<Brand> brands = Arrays.asList(
            new Brand(1, "Brand 1","descri"),
            new Brand(2, "Brand 2","descri")
        );
        int currentPage = 1;
        int size = 10;
        int totalPages = 5;
        int totalData = 50;

        PaginBrand paginBrand = new PaginBrand(brands, currentPage, size, totalPages, totalData);

        // Act
        List<Brand> newBrands = Arrays.asList(
            new Brand(3, "Brand 3","descr")
        );
        paginBrand.setBrand(newBrands);
        paginBrand.setCurrentPage(2);
        paginBrand.setSize(5);
        paginBrand.setTotalPages(3);
        paginBrand.setTotalData(15);

        // Assert
        assertEquals(newBrands, paginBrand.getBrands());
        assertEquals(2, paginBrand.getCurrentPage());
        assertEquals(5, paginBrand.getSize());
        assertEquals(3, paginBrand.getTotalPages());
        assertEquals(15, paginBrand.getTotalData());
    }

    @Test
    void testEmptyConstructor() {
        // Arrange
        PaginBrand paginBrand = new PaginBrand(null, null, null, null, null);

        // Act & Assert
        assertNull(paginBrand.getBrands());
        assertNull(paginBrand.getCurrentPage());
        assertNull(paginBrand.getSize());
        assertNull(paginBrand.getTotalPages());
        assertNull(paginBrand.getTotalData());
    }
}

