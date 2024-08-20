package com.microstock.apistock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.microstock.apistock.aplicacion.dtos.CategoriaDto;
import com.microstock.apistock.aplicacion.excepciones.excepciones_categoria.ErroresCategoria;
import com.microstock.apistock.aplicacion.mappers.MapperCategoria;
import com.microstock.apistock.aplicacion.servicios.ServicioPostCategoria;
import com.microstock.apistock.dominio.entity.Categoria;
import com.microstock.apistock.dominio.servicios.ServicioCategoriaPost;
import com.microstock.apistock.infraestructura.interfaces.ICategoriaRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CategoriaServicioTest {

	@Mock
    private ServicioCategoriaPost servicioEntity;
    @Mock
    private MapperCategoria mapperCategoria;
    @Mock
    private ICategoriaRepository repository;

    private ServicioPostCategoria servicioPostCategoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servicioPostCategoria = new ServicioPostCategoria(servicioEntity, mapperCategoria, repository);
    }

    @Test
void testCrearCat_NombreDemasiadoLargo() {
    CategoriaDto catedto = new CategoriaDto();
    catedto.setNombre("NombreMuyLargoQueSuperaLos50CaracteresPermitidossss");
    catedto.setDescripcion("descripcion valida");
    ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
        servicioPostCategoria.crearCat(catedto);
    });

    assertEquals("El nombre debe tener menos de 50 caracteres", exception.getMessage());
}

@Test
void testCrearCat_NombreNulo() {
    CategoriaDto catedto = new CategoriaDto();
    catedto.setNombre("");
    catedto.setDescripcion("descripcion valida");
    ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
        servicioPostCategoria.crearCat(catedto);
    });

    assertEquals("El nombre debe tener al menos 1 caracter", exception.getMessage());
}

@Test
void testCrearCat_NombreYaExistente() {
     CategoriaDto categoriaDto = new CategoriaDto("Nombre Existente", "Descripción válida");
     Categoria categoriaExistente = new Categoria(1, "Nombre Existente", "Descripción existente");

        when(repository.findByNombreIgnoreCase(categoriaDto.getNombre())).thenReturn(Optional.of(categoriaExistente));

        ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
            servicioPostCategoria.crearCat(categoriaDto);
        });

        assert(exception.getMessage().equals("El nombre ya existe"));

        verify(repository, times(1)).findByNombreIgnoreCase(categoriaDto.getNombre());

        verify(servicioEntity, never()).CrearCategoria(any());
    }

    @Test
    void descripcionDemasiadoLarga() {
        CategoriaDto catedto = new CategoriaDto();
        catedto.setNombre("Nombre valido");
        catedto.setDescripcion("descripcion que supera la capacidad delimitada por el cliente"
        +" que no puede pasar los 90 caracteres para la descripcion de su categoria y teniendo esto en cuenta se hace el test");
        ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
            servicioPostCategoria.crearCat(catedto);
        });
    
        assertEquals("la descripcion debe tener menos de 90 caracteres", exception.getMessage());
    }

    @Test
    void descripcionnula() {
        CategoriaDto catedto = new CategoriaDto();
        catedto.setNombre("Nombre valido");
        catedto.setDescripcion("");
        ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
            servicioPostCategoria.crearCat(catedto);
        });
    
        assertEquals("La descripcion debe tener al menos 1 caracter", exception.getMessage());
    }
    @Test
void testCrearCat_Exito() {
    CategoriaDto categoriaDto = new CategoriaDto();
    categoriaDto.setNombre("Categoria Nueva");
    categoriaDto.setDescripcion("Descripcion válida");

    Categoria categoria = new Categoria();
    when(repository.findByNombreIgnoreCase("Categoria Nueva")).thenReturn(Optional.empty());
    when(mapperCategoria.CategoriaDtoToCategoria(categoriaDto)).thenReturn(categoria);
    when(servicioPostCategoria.crearCat(categoriaDto)).thenReturn(categoriaDto);
    when(mapperCategoria.CategoriatoToCategoriaDto(categoria)).thenReturn(categoriaDto);

    CategoriaDto resultado = servicioPostCategoria.crearCat(categoriaDto);

    assertNotNull(resultado);
    assertEquals("Categoria Nueva", resultado.getNombre());
}
}