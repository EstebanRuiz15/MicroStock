package com.microstock.apistock.categoriatest;
import com.fasterxml.jackson.databind.ObjectMapper;
    import com.microstock.apistock.aplicacion.dtos.CategoriaDto;
    import com.microstock.apistock.aplicacion.servicios.ServicioCategoria;
import com.microstock.apistock.infraestructura.controllers.ControllerCategoria;

import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.when;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
    
@WebMvcTest(ControllerCategoria.class)
class ControllerTest {
    
        @Autowired
        private MockMvc mockMvc;
    
        @MockBean
        private ServicioCategoria servicioCategoria;
    
        @InjectMocks
        private ControllerCategoria controllerCategoria;
    
        private ObjectMapper objectMapper;
    
        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            objectMapper = new ObjectMapper();
        }
    
        @Test
        void testCrearCategoria_Success() throws Exception {
            // Arrange
            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setNombre("Categoria Nueva");
            categoriaDto.setDescripcion("Descripcion válida");
    
            when(servicioCategoria.crearCat(any(CategoriaDto.class))).thenReturn(categoriaDto);
    
            // Act & Assert
            mockMvc.perform(post("/Api/Stock/agregar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(categoriaDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nombre").value("Categoria Nueva"))
                    .andExpect(jsonPath("$.descripcion").value("Descripcion válida"));
        }
    
        @Test
        void testCrearCategoria_Failure() throws Exception {
            // Arrange
            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setNombre("Categoria Inválida");
            categoriaDto.setDescripcion("Descripcion inválida");
    
            when(servicioCategoria.crearCat(any(CategoriaDto.class))).thenThrow(new RuntimeException("Error al crear categoría"));
    
            // Act & Assert
            mockMvc.perform(post("/Api/Stock/agregar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(categoriaDto)))
                    .andExpect(status().isInternalServerError());
        }
    }

