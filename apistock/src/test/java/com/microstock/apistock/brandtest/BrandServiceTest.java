package com.microstock.apistock.brandtest;

import com.microstock.apistock.domain.exception.ErrorException;
import com.microstock.apistock.domain.interfaces.IBrandRepositoryPort;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.services.ServiceBrandImpl;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.domain.util.PaginBrand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BrandServiceTest {
    

    @Mock
    private IBrandRepositoryPort repository;

    @InjectMocks
    private ServiceBrandImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(IBrandRepositoryPort.class);
        service = new ServiceBrandImpl(repository);
    }

   
    @Test
    void createBrand_NameAlreadyExists() {
        Brand brand = new Brand(1, "ExistingBrand", "Description");

        when(repository.findByNombreIgnoreCase(any())).thenReturn(Optional.of(brand));

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.createBrand(brand));
        assertEquals(ConstantsDomain.NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE, thrown.getMessage());
    }

   
    @Test
    void createBrand_NameTooLong() {
        Brand brand = new Brand(1, "ThisNameIsWayTooLongToBeAcceptedByTheSystemmoreargename", "Description");

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.createBrand(brand));
        assertEquals(ConstantsDomain.NAME_MAX_CHARACTERS_EXCEPTION_MESSAGE, thrown.getMessage());
    }

   
    @Test
    void createBrand_NameTooShort() {
        Brand brand = new Brand(1, "", "Description");

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.createBrand(brand));
        assertEquals(ConstantsDomain.NAME_NOT_NULL_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    
    @Test
    void createBrand_DescriptionTooLong() {
        Brand brand = new Brand(1, "BrandName", "a".repeat(121));

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.createBrand(brand));
        assertEquals(ConstantsDomain.DESCRIPTION_MAX_CHARACTERS_BRAND_EXCEPTION_MESSAGE, thrown.getMessage());
    }

   
    @Test
    void createBrand_DescriptionTooShort() {
        Brand brand = new Brand(1, "BrandName", "");

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.createBrand(brand));
        assertEquals(ConstantsDomain.DESCRIPTION_NOT_NULL_EXCEPTION_MESSAGE, thrown.getMessage());
    }

   
    @Test
    void getAllBrand_Success() {
    
        Integer page = 0;
        Integer size = 10;
        String orden = "asc";

        List<Brand> allBrands = Arrays.asList(
            new Brand(1, "Brand1", "Description1"),
            new Brand(2, "Brand2", "Description2"),
            new Brand(3, "Brand3", "Description3")
        );

    
        when(repository.findByBrand(orden)).thenReturn(allBrands);
        
        PaginBrand result = service.getAllBrand(page, size, orden);

        assertNotNull(result,"El resultado no debe ser nulo");
        assertEquals(3, result.getBrands().size(),"el tamanio de las marcas debe ser 3");
        assertEquals(page, result.getCurrentPage(), "la pagina actual debe ser 0");
        assertEquals(size, result.getSize(),"el tamanio de la pagina debe ser 10");
        assertEquals(1, result.getTotalPages(), "el total de paginas debe ser 1");
        assertEquals(3, result.getTotalData(), "el total de datos debe ser 3");
    }

    
    @Test
    void getAllBrand_InvalidOrder() {
        Integer page = 0;
        Integer size = 2;
        String orden = "invalid_order";

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.getAllBrand(page, size, orden));
        assertEquals(ConstantsDomain.ORDEN_DIFERENT_ASC_OR_DESC_EXCEPTION_MESSAGE, thrown.getMessage());
    }

   
    @Test
    void getAllBrand_InvalidPage() {
        Integer page = -1;
        Integer size = 2;
        String orden = "asc";

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.getAllBrand(page, size, orden));
        assertEquals(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    
    @Test
    void getAllBrand_InvalidSize() {
        Integer page = 0;
        Integer size = 0;
        String orden = "asc";

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.getAllBrand(page, size, orden));
        assertEquals(ConstantsDomain.SIZE_MIN_CHARACTER_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    
    @Test
    void getAllBrand_NoBrandsFound() {
        Integer page = 0;
        Integer size = 2;
        String orden = "asc";

        when(repository.findByBrand(orden)).thenReturn(Collections.emptyList());

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.getAllBrand(page, size, orden));
        assertEquals(ConstantsDomain.NO_DATA_BRANDS_EXCEPTION_MESSAGE, thrown.getMessage());
    }

   
    @Test
    void getAllBrand_EmptyPage() {
        Integer page = 1; 
        Integer size = 2;
        String orden = "asc";

        List<Brand> brands = Arrays.asList(new Brand(1, "Brand1", "Description1"));
        when(repository.findByBrand(orden)).thenReturn(brands);

        ErrorException thrown = assertThrows(ErrorException.class, () -> service.getAllBrand(page, size, orden));
        assertEquals(ConstantsDomain.NO_BRANDS_FOUND_EXCEPTION_MESSAGE + ConstantsDomain.TOTAL_PAGES + 1, thrown.getMessage());
    }
}
