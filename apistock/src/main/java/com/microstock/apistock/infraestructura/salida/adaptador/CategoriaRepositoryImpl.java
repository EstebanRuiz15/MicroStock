package com.microstock.apistock.infraestructura.salida.adaptador;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.microstock.apistock.dominio.interfaces.ICategoriaRepositoryPort;
import com.microstock.apistock.dominio.modelo.Categoria;
import com.microstock.apistock.infraestructura.salida.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructura.salida.repositorios.CategoriaRepositoryJpa;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryImpl implements ICategoriaRepositoryPort{
    private final CategoriaRepositoryJpa jpaRepository;
    private final ICategoriaToEntitymapper mapperEntity;
    @Override
    public void saveCategoria(Categoria categoria) {
         jpaRepository.save(mapperEntity.toCategoriaEntity(categoria));
    }

    @Override
    public Optional<Categoria> findByNombreIgnoreCase(String nombre) {
        return jpaRepository.findByNombreIgnoreCase(nombre);
    }

    
}
