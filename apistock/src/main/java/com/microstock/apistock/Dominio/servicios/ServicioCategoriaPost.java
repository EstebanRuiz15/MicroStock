package com.microstock.apistock.dominio.servicios;

import com.microstock.apistock.dominio.entity.Categoria;
import com.microstock.apistock.infraestructura.interfaces.ICategoriaRepository;

public class ServicioCategoriaPost {

    private final ICategoriaRepository repositori;

    public ServicioCategoriaPost(ICategoriaRepository repository){
        this.repositori=repository;
    }
    
    public Categoria CrearCategoria(Categoria categoria){
        return repositori.save(categoria);
    }
}
