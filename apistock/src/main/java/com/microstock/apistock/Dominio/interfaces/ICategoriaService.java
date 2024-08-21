package com.microstock.apistock.dominio.interfaces;

import com.microstock.apistock.dominio.entity.Categoria;
import java.util.List;
public interface ICategoriaService {

     Categoria crearCategoria(Categoria categoria);
     List<Categoria> getAllCategoria(Integer page, Integer size);
} 
