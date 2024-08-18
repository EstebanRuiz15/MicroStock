package com.microstock.apistock.aplicacion.servicios;

import com.microstock.apistock.aplicacion.dtos.CategoriaDto;
import com.microstock.apistock.aplicacion.excepciones.excepciones_categoria.ErroresCategoria;
import com.microstock.apistock.aplicacion.mappers.MapperCategoria;
import com.microstock.apistock.dominio.entity.Categoria;
import com.microstock.apistock.dominio.servicios.ServicioCategoriaPost;
import com.microstock.apistock.infraestructura.interfaces.ICategoriaRepository;

import java.util.Optional;


public class ServicioPostCategoria  {
    private final ServicioCategoriaPost servicientity;
    private final MapperCategoria mapperCategoria;
    private final ICategoriaRepository repositori;    
   
     public ServicioPostCategoria(ServicioCategoriaPost servicientity, MapperCategoria mapperCategoria,ICategoriaRepository repository) {
        this.servicientity = servicientity;
        this.mapperCategoria=mapperCategoria;
        this.repositori=repository;
    }

    public CategoriaDto crearCat(CategoriaDto categoria) {
        
         Optional <Categoria> exietente= repositori.findByNombreIgnoreCase(categoria.getNombre());
        
            if(categoria.getNombre().length()>50){
                throw new ErroresCategoria("El nombre debe tener menos de 50 caracteres");
             
            }
            if(categoria.getNombre().length()<1){
                throw new ErroresCategoria("El nombre debe tener al menos 1 caracter");
             
            }
            if(categoria.getDescripcion().length()>90){
                throw new ErroresCategoria("la descripcion debe tener menos de 90 caracteres");
             
            }
            if(categoria.getDescripcion().length()<1){
                throw new ErroresCategoria("La descripcion debe tener al menos 1 caracter");
            }

            if(exietente.isPresent()){
                throw new ErroresCategoria("El nombre ya existe");
            }
            Categoria categoriaEntity= servicientity.CrearCategoria(mapperCategoria.CategoriaDtoToCategoria(categoria));
        return  mapperCategoria.CategoriatoToCategoriaDto(categoriaEntity);
    }
}
