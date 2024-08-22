package com.microstock.apistock.dominio.interfaces;

import java.util.Optional;

import com.microstock.apistock.dominio.modelo.Categoria;

public interface ICategoriaRepositoryPort {
    void saveCategoria(Categoria categoria);
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
    
}