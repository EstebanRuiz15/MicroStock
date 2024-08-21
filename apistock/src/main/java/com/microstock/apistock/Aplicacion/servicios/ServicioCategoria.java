package com.microstock.apistock.aplicacion.servicios;

import com.microstock.apistock.aplicacion.dtos.CategoriaDto;
import com.microstock.apistock.aplicacion.mappers.MapperCategoria;
import com.microstock.apistock.dominio.entity.Categoria;
import com.microstock.apistock.dominio.interfaces.ICategoriaService;


public class ServicioCategoria  {
    private final ICategoriaService iCategoriaService;
    private final MapperCategoria mapperCategoria;   
   
     public ServicioCategoria(ICategoriaService iCategoriaService, MapperCategoria mapperCategoria) {
        this.iCategoriaService = iCategoriaService;
        this.mapperCategoria=mapperCategoria;
       
    }

    public CategoriaDto crearCat(CategoriaDto categoria) {
        
         Categoria categoriaEntity= iCategoriaService.crearCategoria(mapperCategoria.categoriaDtoToCategoria(categoria));
        return  mapperCategoria.categoriatoToCategoriaDto(categoriaEntity);
    }
}
