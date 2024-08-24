package com.microstock.apistock.categoriatest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driving_http.controllers.ControllerCategoria;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.CategoryDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IMapperPeticionAdd;

import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
    
@WebMvcTest(ControllerCategoria.class)
class ControllerTest {
     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService serviceCategoria;

    @MockBean
    private IMapperPeticionAdd mapperadd;

    private CategoryDtoAdd categoriaDtoAgre;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        categoriaDtoAgre = new CategoryDtoAdd();
        categoriaDtoAgre.setName("Nueva Categoria");
        categoriaDtoAgre.setDescription("Descripción válida");

        objectMapper = new ObjectMapper();
    }

    @Test
    void testCrearCategoria_Created() throws Exception {
        // Mock del mapeo y creación de categoría
        doNothing().when(serviceCategoria).createCategory(any());
        when(mapperadd.toCategoria(any(CategoryDtoAdd.class))).thenReturn(new Category(1,"nombre","descri"));

        mockMvc.perform(post("/Api/Stock/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriaDtoAgre)))
                .andExpect(status().isCreated());

        verify(serviceCategoria, times(1)).createCategory(any());
    }

    @Test
    void testCrearCategoria_Error() throws Exception {
        // Simulamos un error en la creación de la categoría
        doThrow(new RuntimeException("Error al crear categoría")).when(serviceCategoria).createCategory(any());

        mockMvc.perform(post("/Api/Stock/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriaDtoAgre)))
                .andExpect(status().isInternalServerError());

        verify(serviceCategoria, times(1)).createCategory(any());
    }
    }

