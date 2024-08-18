package com.microstock.apistock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

@SpringBootTest
class CategoriaServicioTest {

	@Mock
    private ServicioCategoriaPost servicioCategoriaPost;
    @Mock
    private MapperCategoria mapperCategoria;
    @Mock
    private ICategoriaRepository iCategoriaRepository;
    @Mock
    private Categoria servicientity;

    @InjectMocks
    private ServicioPostCategoria servicioPostCategoria;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar mocks
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
    when(iCategoriaRepository.findByNombreIgnoreCase("NombreExistente"))
            .thenReturn(Optional.of(new Categoria("NombreExistente", "descripcion valida")));

        // Configura el objeto de entrada para el método
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre("NombreExistente");
        categoriaDto.setDescripcion("descripcion valida");

        // Verifica que la excepción sea lanzada cuando se llama al método crearCat
        ErroresCategoria exception = assertThrows(ErroresCategoria.class, () -> {
            servicioPostCategoria.crearCat(categoriaDto);
        });

        // Verifica el mensaje de la excepción
        assertEquals("El nombre ya existe", exception.getMessage());
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
}