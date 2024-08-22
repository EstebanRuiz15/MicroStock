package com.microstock.apistock.infraestructura.salida.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microstock.apistock.dominio.modelo.Categoria;
import com.microstock.apistock.infraestructura.salida.entity.CategoriaEntity;

import java.util.Optional;


@Repository
public interface CategoriaRepositoryJpa extends JpaRepository<CategoriaEntity, Integer> {
    Optional<Categoria> findByNombreIgnoreCase(String nombre);

}