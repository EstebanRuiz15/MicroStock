package com.microstock.apistock.categoriatest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.microstock.apistock.dominio.excepciones.excepciones_categoria.ErroresCategoria;
import com.microstock.apistock.dominio.interfaces.ICategoriaRepositoryPort;
import com.microstock.apistock.dominio.modelo.Categoria;
import com.microstock.apistock.dominio.servicios.ServicioCategoriaImpl;
import com.microstock.apistock.infraestructura.salida.entity.CategoriaEntity;


@SpringBootTest
class CategoriaServicioTest {

    @Mock
    private ICategoriaRepositoryPort categoriaRepository;

    @InjectMocks
    private ServicioCategoriaImpl servicioCategoria;

    @BeforeEach
    void setUp() {
        categoriaRepository = mock(ICategoriaRepositoryPort.class);
        servicioCategoria = new ServicioCategoriaImpl(categoriaRepository);
    }


    @Test
void testCrearCat_NombreDemasiadoLargo() {
    Categoria categoria = new Categoria(1,"Nombre muy muy muy largo que excede los 50 caracteres", "Descripción válida");

    ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
        servicioCategoria.crearCategoria(categoria);
    });

    assertEquals("El nombre debe tener menos de 50 caracteres", exception.getMessage());
}

@Test
void testCrearCat_NombreNulo() {
    Categoria cate = new Categoria(1,"","descripcion valida");
    ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
        servicioCategoria.crearCategoria(cate);
    });

    assertEquals("El nombre no debe estar nulo", exception.getMessage());
}

@Test
void testCrearCat_NombreYaExistente() {
    Categoria categoriaExistente = new Categoria(1,"Existente", "Descripción existente");
    when(categoriaRepository.findByNombreIgnoreCase("Existente"))
            .thenReturn(Optional.of(categoriaExistente));

    Categoria nuevaCategoria = new Categoria(2,"Existente", "Nueva descripción");

    // Act & Assert
    ErroresCategoria excepcion = assertThrows(ErroresCategoria.class, () -> {
        servicioCategoria.crearCategoria(nuevaCategoria);
    });

    assertEquals("El nombre ya existe", excepcion.getMessage());

    verify(categoriaRepository, never()).saveCategoria(any(Categoria.class));
    }

    @Test
    void descripcionDemasiadoLarga() {
        Categoria categoria = new Categoria(1,"Nombre válido", "Descripción muy muy muy larga que excede los 90 caracteres"
                                                                        +"Esta descripcion deberia tener demasiados caracteres para implementar la excepcion");

        ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
            servicioCategoria.crearCategoria(categoria);
        });

        assertEquals("la descripcion debe tener menos de 90 caracteres", exception.getMessage());
    }

    @Test
    void descripcionnula() {
        Categoria categoria = new Categoria(1,"Nombre válido", "");

        ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
            servicioCategoria.crearCategoria(categoria);
        });

        assertEquals("La descripcion no debe ser nula", exception.getMessage());
    }
    @Test
void testCrearCat_Exito() {
    Categoria categoriaValida = new Categoria(1,"Categoria Valida", "Descripción válida");
     
     when(categoriaRepository.findByNombreIgnoreCase(categoriaValida.getNombre())).thenReturn(Optional.empty());

     servicioCategoria.crearCategoria(categoriaValida);

     // Verificaciones
     verify(categoriaRepository).findByNombreIgnoreCase(categoriaValida.getNombre()); // Se verificó que se realizó la búsqueda
     verify(categoriaRepository).saveCategoria(categoriaValida); // Se verificó que se guardó la categoría
 }
}
