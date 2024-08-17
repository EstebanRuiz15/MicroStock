package com.microstock.apistock.InfraEstructura.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microstock.apistock.Dominio.Entity.Categoria;


@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}