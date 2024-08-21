package com.microstock.apistock.dominio.interfaces;

import java.util.Optional;

import com.microstock.apistock.dominio.entity.Categoria;

public interface ICategoriaRepository {
    Categoria save(Categoria categoria);
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
    
}