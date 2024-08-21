package com.microstock.apistock.dominio.servicios;

import java.util.List;

import com.microstock.apistock.dominio.entity.Categoria;
import com.microstock.apistock.dominio.excepciones.excepciones_categoria.ErroresCategoria;
import com.microstock.apistock.dominio.interfaces.ICategoriaRepository;
import com.microstock.apistock.dominio.interfaces.ICategoriaService;
import java.util.Optional;

public class ServicioCategoriaImpl implements ICategoriaService{

    private final ICategoriaRepository repositorio;

    public ServicioCategoriaImpl(ICategoriaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        Optional <Categoria> exietente= repositorio.findByNombreIgnoreCase(categoria.getNombre());
            if(exietente.isPresent()){
            throw new ErroresCategoria("El nombre ya existe");
        }
        
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

            
        return repositorio.save(categoria);
    }

    @Override
    public List<Categoria> getAllCategoria(Integer page, Integer size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategoria'");
    }

   
}