package com.microstock.apistock.brandtest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.util.PaginBrand;
import com.microstock.apistock.infraestructur.driving_http.controllers.ControllerBrand;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.BrandDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IBrandRequestAddMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

@SpringBootTest   
@AutoConfigureMockMvc
class ControllerBrandTest {
    private MockMvc mockMvc;

    @Mock
    private IBrandService brandService;

    @Mock
    private IBrandRequestAddMapper brandRequestMapper;

    @InjectMocks
    private ControllerBrand controllerBrand;

    private BrandDtoAdd brandDtoAdd;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        brandDtoAdd = new BrandDtoAdd();
        brandDtoAdd.setName("New brand");
        brandDtoAdd.setDescription("Descripción válida");
        mockMvc = MockMvcBuilders.standaloneSetup(controllerBrand).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void addProduct_Success() throws Exception {

        doNothing().when(brandService).createBrand(any());
         when(brandRequestMapper.toBrand(any(BrandDtoAdd.class))).thenReturn(new Brand (1,"nombre","descri"));
        mockMvc = MockMvcBuilders.standaloneSetup(controllerBrand).build();

        mockMvc.perform(post("/brand/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandDtoAdd)))
                .andExpect(status().isCreated());
        verify(brandService, times(1)).createBrand(any());
    }

    @Test
    void addProduct_Failure() throws Exception {
        doThrow(new RuntimeException("Error al crear la marca")).when(brandService).createBrand(any());

        mockMvc = MockMvcBuilders.standaloneSetup(controllerBrand).build();

        mockMvc.perform(post("/brand/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandDtoAdd)))
                .andExpect(status().isInternalServerError());
    
    verify(brandService, times(1)).createBrand(any());
   } 
   
    @Test
     void testGetAllBrands_Success() throws Exception {
        Integer page = 0;
        Integer size = 10;
        String orden = "asc";

        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1,"name","descri")); 
        brands.add(new Brand(2,"name 2", "descri 2"));

        PaginBrand paginBrand = new PaginBrand(
            brands,
            page,
            size,
            1, 
            2  
        );

        when(brandService.getAllBrand(page, size, orden)).thenReturn(paginBrand);

        mockMvc = MockMvcBuilders.standaloneSetup(controllerBrand).build();
        mockMvc.perform(get("/brand/?page=0&size=10&orden=asc"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.currentPage").value(page))
            .andExpect(jsonPath("$.size").value(size))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.totalData").value(2));
    }


}