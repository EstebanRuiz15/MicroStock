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
import com.microstock.apistock.infraestructur.driving_http.controllers.ControllerBrand;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.BrandDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IBrandRequestAddMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

@SpringBootTest   
@AutoConfigureMockMvc
class BrandServiceTest {
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
        brandDtoAdd.setName("Nueva Categoria");
        brandDtoAdd.setDescription("Descripción válida");

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
}