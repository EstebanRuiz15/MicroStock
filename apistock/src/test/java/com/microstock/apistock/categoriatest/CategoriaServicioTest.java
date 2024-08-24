package com.microstock.apistock.categoriatest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorCategory;
import com.microstock.apistock.domain.interfaces.ICategoryRepositoryPort;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.services.ServiceCategoryImpl;
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

    ErrorCategory exception = assertThrows(ErrorCategory.class, () -> {
        servicioCategoria.createCategory(category);
    });

    assertEquals("The name must be less than 50 characters", exception.getMessage());
}

@Test
void testCrearCat_NombreNulo() {
    Category cate = new Category(1,"","descripcion valida");
    ErrorCategory exception = assertThrows(ErrorCategory.class, () -> {
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
    ErrorCategory excepcion = assertThrows(ErrorCategory.class, () -> {
        servicioCategoria.createCategory(nuevaCategoria);
    });

    assertEquals("The name is already exists", excepcion.getMessage());

    verify(categoriaRepository, never()).saveCategory(any(Category.class));
    }

    @Test
    void descripcionDemasiadoLarga() {
        Category categoria = new Category(1,"Nombre válido", "Descripción muy muy muy larga que excede los 90 caracteres"
                                                                        +"Esta descripcion deberia tener demasiados caracteres para implementar la excepcion");

        ErrorCategory exception = assertThrows(ErrorCategory.class, () -> {
            servicioCategoria.createCategory(categoria);
        });

        assertEquals("The description must be less than 90 characters", exception.getMessage());
    }

    @Test
    void descripcionnula() {
        Category categoria = new Category(1,"Nombre válido", "");

        ErrorCategory exception = assertThrows(ErrorCategory.class, () -> {
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
}
