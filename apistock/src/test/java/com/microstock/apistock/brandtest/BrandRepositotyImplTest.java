package com.microstock.apistock.brandtest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.infraestructur.driven_rp.adapter.BrandRepositoryImpl;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IBrandToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.BrandRepositoryJpa;

 class BrandRepositotyImplTest {

    @Mock
    private BrandRepositoryJpa jparepository;

    @Mock
    private IBrandToEntityMapper brandToEntityMapper;

    @InjectMocks
    private BrandRepositoryImpl brandRepository;
    
    private Brand brand=new Brand(1,"name","descr");
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   
    @Test
    void testSaveBrand() {
        
        BrandEntity brandEntity = new BrandEntity();
        when(brandToEntityMapper.toBrandEntity(brand)).thenReturn(brandEntity);

        // Act
        brandRepository.saveBrand(brand);

        // Assert
        verify(jparepository, times(1)).save(brandEntity);
    }

    @Test
    void testFindByNombreIgnoreCase() {
        // Arrange
        String name = "BrandName";
        when(jparepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(brand));

        // Act
        Optional<Brand> result = brandRepository.findByNombreIgnoreCase(name);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(brand, result.get());
    }

    @Test
    void testFindByBrand() {
        // Arrange
        String orden = "asc";
        BrandEntity brandEntity1 = new BrandEntity();
        BrandEntity brandEntity2 = new BrandEntity();
        List<BrandEntity> brandEntities = Arrays.asList(brandEntity1, brandEntity2);
        when(jparepository.findAll(any(Sort.class))).thenReturn(brandEntities);
        when(brandToEntityMapper.toBrand(brandEntity1)).thenReturn(brand);
        when(brandToEntityMapper.toBrand(brandEntity2)).thenReturn(brand);

        // Act
        List<Brand> result = brandRepository.findByBrand(orden);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(brand));
        verify(jparepository, times(1)).findAll(any(Sort.class));
    }

    @Test
    void testFindById() {
        // Arrange
        Integer id = 1;
        BrandEntity brandEntity = new BrandEntity();
        when(jparepository.findById(id)).thenReturn(Optional.of(brandEntity));

        // Act
        Optional<BrandEntity> result = brandRepository.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(brandEntity, result.get());
    }

}
