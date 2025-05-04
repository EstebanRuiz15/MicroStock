package com.microstock.apistock.categoriatest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.microstock.apistock.domain.exception.ErrorException;
import com.microstock.apistock.domain.interfaces.ICategoryRepositoryPort;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.services.ServiceCategoryImpl;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.domain.util.PaginCategory;
@SpringBootTest
@AutoConfigureMockMvc
class CategoriaServicioTest {

    @Mock
    private ICategoryRepositoryPort categoriaRepository;

    @InjectMocks
    private ServiceCategoryImpl servicioCategoria;

    @BeforeEach
    void setUp() {
        categoriaRepository = mock(ICategoryRepositoryPort.class);
        servicioCategoria = new ServiceCategoryImpl(categoriaRepository);
    }


    @Test
void testCrearCat_NombreDemasiadoLargo() {
    Category category = new Category(1,"Nombre muy muy muy largo que excede los 50 caracteres", "Descripción válida");

    ErrorException exception = assertThrows(ErrorException.class, () -> {
        servicioCategoria.createCategory(category);
    });

    assertEquals("The name must be less than 50 characters", exception.getMessage());
}

@Test
void testCrearCat_NombreNulo() {
    Category cate = new Category(1,"","descripcion valida");
    ErrorException exception = assertThrows(ErrorException.class, () -> {
        servicioCategoria.createCategory(cate);
    });

    assertEquals("Name cannot be null", exception.getMessage());
}

@Test
void testCrearCat_NombreYaExistente() {
    Category categoriaExistente = new Category(1,"Existente", "Descripción existente");
    when(categoriaRepository.findByNombreIgnoreCase("Existente"))
            .thenReturn(Optional.of(categoriaExistente));

    Category nuevaCategoria = new Category(2,"Existente", "Nueva descripción");

    // Act & Assert
    ErrorException excepcion = assertThrows(ErrorException.class, () -> {
        servicioCategoria.createCategory(nuevaCategoria);
    });

    assertEquals("The name is already exists", excepcion.getMessage());

    verify(categoriaRepository, never()).saveCategory(any(Category.class));
    }

    @Test
    void descripcionDemasiadoLarga() {
        Category categoria = new Category(1,"Nombre válido", "Descripción muy muy muy larga que excede los 90 caracteres"
                                                                        +"Esta descripcion deberia tener demasiados caracteres para implementar la excepcion");

        ErrorException exception = assertThrows(ErrorException.class, () -> {
            servicioCategoria.createCategory(categoria);
        });

        assertEquals("The description must be less than 90 characters", exception.getMessage());
    }

    @Test
    void descripcionnula() {
        Category categoria = new Category(1,"Nombre válido", "");

        ErrorException exception = assertThrows(ErrorException.class, () -> {
            servicioCategoria.createCategory(categoria);
        });

        assertEquals("Description cannot be null", exception.getMessage());
    }
    @Test
void testCrearCat_Exito() {
    Category categoriaValida = new Category(1,"Categoria Valida", "Descripción válida");
     
     when(categoriaRepository.findByNombreIgnoreCase(categoriaValida.getName())).thenReturn(Optional.empty());

     servicioCategoria.createCategory(categoriaValida);

     // Verificaciones
     verify(categoriaRepository).findByNombreIgnoreCase(categoriaValida.getName()); // Se verificó que se realizó la búsqueda
     verify(categoriaRepository).saveCategory(categoriaValida); // Se verificó que se guardó la categoría
 }
  @Test
    void testGetAllCategory_InvalidOrden() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String orden = "invalid";

        ErrorException exception = assertThrows(ErrorException.class, () -> {
            servicioCategoria.getAllCategory(page, size, orden);
        });
        assertEquals(ConstantsDomain.ORDEN_DIFERENT_ASC_OR_DESC_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetAllCategory_InvalidPage() {
        // Arrange
        Integer page = -1;
        Integer size = 10;
        String orden = "asc";

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () -> {
            servicioCategoria.getAllCategory(page, size, orden);
        });
        assertEquals(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetAllCategory_InvalidSize() {
        // Arrange
        Integer page = 0;
        Integer size = 0;
        String orden = "asc";

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () -> {
            servicioCategoria.getAllCategory(page, size, orden);
        });
        assertEquals(ConstantsDomain.SIZE_MIN_CHARACTER_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void testGetAllCategory_EmptyResult() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String orden = "asc";
        List<Category> allCategories = new ArrayList<>();
        when(categoriaRepository.findByCategorias(orden)).thenReturn(allCategories);

        // Act & Assert
        ErrorException exception = assertThrows(ErrorException.class, () -> {
            servicioCategoria.getAllCategory(page, size, orden);
        });
        assertEquals(ConstantsDomain.NO_DATA_CATEGORY_EXCEPTION_MESSAGE, exception.getMessage());
    }
    @Test
    void testGetAllCategory_ValidParameters() {
    Integer page = 0;  
    Integer size = 10; 
    String orden = "asc"; 

    List<Category> allCategories = new ArrayList<>();
    allCategories.add(new Category(1,"nombre","descripcion")); 
    allCategories.add(new Category(2,"nombre2","descripcion 2"));

 
    when(categoriaRepository.findByCategorias(orden)).thenReturn(allCategories);


    PaginCategory result = servicioCategoria.getAllCategory(page, size, orden);


    assertNotNull(result, "El resultado no debe ser nulo.");
    assertEquals(2, result.getCategory().size(), "El tamaño de la categoría debe ser 2.");
    assertEquals(page, result.getCurrentPage(), "La página actual debe ser 0.");
    assertEquals(size, result.getSize(), "El tamaño de la página debe ser 10.");
    assertEquals(1, result.getTotalPages(), "El total de páginas debe ser 1.");
    assertEquals(2, result.getTotalData(), "El total de datos debe ser 2.");
}
}
