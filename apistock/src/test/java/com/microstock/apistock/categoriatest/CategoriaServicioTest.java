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
import com.microstock.apistock.dominio.entity.Categoria;
import com.microstock.apistock.dominio.excepciones.excepciones_categoria.ErroresCategoria;
import com.microstock.apistock.dominio.interfaces.ICategoriaRepository;
import com.microstock.apistock.dominio.servicios.ServicioCategoriaImpl;


@SpringBootTest
class CategoriaServicioTest {

    @Mock
    private ICategoriaRepository categoriaRepository;

    @InjectMocks
    private ServicioCategoriaImpl servicioCategoria;

    @BeforeEach
    void setUp() {
        categoriaRepository = mock(ICategoriaRepository.class);
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
    Categoria cate = new Categoria();
    cate.setNombre("");
    cate.setDescripcion("descripcion valida");
    ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
        servicioCategoria.crearCategoria(cate);
    });

    assertEquals("El nombre debe tener al menos 1 caracter", exception.getMessage());
}

@Test
void testCrearCat_NombreYaExistente() {
    Categoria categoria = new Categoria(1, "Nombre Existente", "Descripción válida");
    Categoria categoriaa = new Categoria(2, "Nombre Existente", "Descripción válida 2");
        when(categoriaRepository.findByNombreIgnoreCase(categoria.getNombre())).thenReturn(Optional.of(categoriaa));

        // Act & Assert
        ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
            servicioCategoria.crearCategoria(categoria);
        });

        // Verificar que el mensaje de error es el esperado
        assert(exception.getMessage().equals("El nombre ya existe"));

        // Verificar que se llamó al método findByNombreIgnoreCase del repositorio
        verify(categoriaRepository, times(1)).findByNombreIgnoreCase(categoria.getNombre());

        // Verificar que no se llamó al método save del repositorio
        verify(categoriaRepository, never()).save(any(Categoria.class));
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

        assertEquals("La descripcion debe tener al menos 1 caracter", exception.getMessage());
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }
    @Test
void testCrearCat_Exito() {
    Categoria categoriaACrear = new Categoria(0, "Categoria Válida", "Descripción válida");
        Categoria categoriaCreadaEnRepositorio = new Categoria(1, "Categoria Válida", "Descripción válida");

        when(categoriaRepository.findByNombreIgnoreCase(categoriaACrear.getNombre())).thenReturn(Optional.empty());
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaCreadaEnRepositorio);

        // Act
        Categoria categoriaCreada = servicioCategoria.crearCategoria(categoriaACrear);

        // Assert
        assertNotNull(categoriaCreada);
        assertEquals(categoriaCreadaEnRepositorio.getId(), categoriaCreada.getId());
        assertEquals(categoriaCreadaEnRepositorio.getNombre(), categoriaCreada.getNombre());
        assertEquals(categoriaCreadaEnRepositorio.getDescripcion(), categoriaCreada.getDescripcion());

        // Verificar que se llamó al método findByNombreIgnoreCase del repositorio
        verify(categoriaRepository, times(1)).findByNombreIgnoreCase(categoriaACrear.getNombre());

        // Verificar que se llamó al método save del repositorio
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }
}
