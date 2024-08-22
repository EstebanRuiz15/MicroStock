package com.microstock.apistock.dominio.servicios;

import java.util.List;

import com.microstock.apistock.dominio.excepciones.excepciones_categoria.ErroresCategoria;
import com.microstock.apistock.dominio.interfaces.ICategoriaRepositoryPort;
import com.microstock.apistock.dominio.interfaces.ICategoriaService;
import com.microstock.apistock.dominio.modelo.Categoria;
import java.util.Optional;

public class ServicioCategoriaImpl implements ICategoriaService{

    private final ICategoriaRepositoryPort repositorio;

    public ServicioCategoriaImpl(ICategoriaRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void crearCategoria(Categoria categoria) {
        Optional <Categoria> exietente= repositorio.findByNombreIgnoreCase(categoria.getNombre().trim());
            if(exietente.isPresent()){
            throw new ErroresCategoria("El nombre ya existe");
        }
        
            if(categoria.getNombre().trim().length()>50){
                throw new ErroresCategoria("El nombre debe tener menos de 50 caracteres");
             
            }
            if(categoria.getNombre().trim().length()<1){
                throw new ErroresCategoria("El nombre no debe estar nulo");
             
            }
            if(categoria.getDescripcion().trim().length()>90){
                throw new ErroresCategoria("la descripcion debe tener menos de 90 caracteres");
             
            }
            if(categoria.getDescripcion().trim().length()<1){
                throw new ErroresCategoria("La descripcion no debe ser nula");
            }

            
         repositorio.saveCategoria(categoria);
    }

    @Override
    public List<Categoria> getAllCategoria(Integer page, Integer size) {
     
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategoria'");
    }

   
}