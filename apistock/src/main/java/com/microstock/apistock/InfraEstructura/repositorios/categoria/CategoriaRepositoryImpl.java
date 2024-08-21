package com.microstock.apistock.infraestructura.repositorios.categoria;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.microstock.apistock.dominio.entity.Categoria;
import com.microstock.apistock.dominio.interfaces.ICategoriaRepository;

@Repository

public class CategoriaRepositoryImpl implements ICategoriaRepository{
    private final CategoriaRepositoryJpa jpaRepository;

    public CategoriaRepositoryImpl(CategoriaRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return jpaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> findByNombreIgnoreCase(String nombre) {
        return jpaRepository.findByNombreIgnoreCase(nombre);
    }
    
}
