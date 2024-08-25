package com.microstock.apistock.categoriatest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorCategory;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.domain.util.PaginCategory;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.CategoryDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IMapperPeticionAdd;

import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;
    
@SpringBootTest   
@AutoConfigureMockMvc
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
       
        doNothing().when(serviceCategoria).createCategory(any());
        when(mapperadd.toCategoria(any(CategoryDtoAdd.class))).thenReturn(new Category(1,"nombre","descri"));

        mockMvc.perform(post("/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriaDtoAgre)))
                .andExpect(status().isCreated());

        verify(serviceCategoria, times(1)).createCategory(any());
    }

    @Test
    void testCrearCategoria_Error() throws Exception {
       
        doThrow(new RuntimeException("Error al crear categoría")).when(serviceCategoria).createCategory(any());

        mockMvc.perform(post("/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriaDtoAgre)))
                .andExpect(status().isInternalServerError());

        verify(serviceCategoria, times(1)).createCategory(any());
    }

    @Test
     void testGetAllCategory_Success() throws Exception {
        Integer page = 0;
        Integer size = 10;
        String orden = "asc";

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1,"name","descri")); 
        categories.add(new Category(2,"name 2", "descri 2"));

        PaginCategory paginCategory = new PaginCategory(
            categories,
            page,
            size,
            1, 
            2  
        );

        when(serviceCategoria.getAllCategory(page, size, orden)).thenReturn(paginCategory);

        
        mockMvc.perform(get("/category/?page=0&size=10&orden=asc"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.currentPage").value(page))
            .andExpect(jsonPath("$.size").value(size))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.totalData").value(2));
    }

@Test
void testGetAllCategory_Failure() throws Exception {
    Integer page = -1;  // invalid parameter
    Integer size = 10;
    String orden = "asc";

   
    when(serviceCategoria.getAllCategory(page, size, orden))
        .thenThrow(new ErrorCategory(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE));

    // Act y Assert: verify that the controller handler the correct exception 
    mockMvc.perform(get("/category/?page=-1&size=10&orden=asc"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.statusCode").value(400))
        .andExpect(jsonPath("$.message").value(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE))
        .andExpect(jsonPath("$.details").value("uri=/category/"));
}
    }

