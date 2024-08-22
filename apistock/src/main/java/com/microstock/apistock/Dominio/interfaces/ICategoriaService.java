package com.microstock.apistock.dominio.interfaces;

import java.util.List;

import com.microstock.apistock.dominio.modelo.Categoria;
public interface ICategoriaService {

     void crearCategoria(Categoria categoria);
     List<Categoria> getAllCategoria(Integer page, Integer size);
} 
