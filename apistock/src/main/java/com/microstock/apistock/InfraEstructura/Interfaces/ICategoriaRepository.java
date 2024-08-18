package com.microstock.apistock.infraestructura.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microstock.apistock.dominio.entity.Categoria;

import java.util.Optional;


@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNombreIgnoreCase(String nombre);

}