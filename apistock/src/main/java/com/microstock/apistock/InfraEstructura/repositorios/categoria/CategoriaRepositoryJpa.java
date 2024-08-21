package com.microstock.apistock.infraestructura.repositorios.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microstock.apistock.dominio.entity.Categoria;

import java.util.Optional;


@Repository
public interface CategoriaRepositoryJpa extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNombreIgnoreCase(String nombre);

}