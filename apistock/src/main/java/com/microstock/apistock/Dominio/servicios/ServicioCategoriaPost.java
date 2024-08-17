package com.microstock.apistock.Dominio.servicios;

import com.microstock.apistock.Dominio.Entity.Categoria;
import com.microstock.apistock.InfraEstructura.Interfaces.ICategoriaRepository;

public class ServicioCategoriaPost {

    private final ICategoriaRepository repositori;

    public ServicioCategoriaPost(ICategoriaRepository repository){
        this.repositori=repository;
    }
    
    public Categoria CrearCategoria(Categoria categoria){
        return repositori.save(categoria);
    }
}
