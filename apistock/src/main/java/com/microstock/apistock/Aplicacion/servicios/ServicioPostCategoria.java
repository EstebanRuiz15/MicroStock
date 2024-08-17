package com.microstock.apistock.Aplicacion.servicios;

import com.microstock.apistock.Aplicacion.Dto.CategoriaDto;
import com.microstock.apistock.Aplicacion.mapper.MapperCategoria;
import com.microstock.apistock.Dominio.Entity.Categoria;
import com.microstock.apistock.Dominio.servicios.ServicioCategoriaPost;


public class ServicioPostCategoria {
    private final ServicioCategoriaPost servicientity;
    private final MapperCategoria mapperCategoria;
    
   
     public ServicioPostCategoria(ServicioCategoriaPost servicientity, MapperCategoria mapperCategoria) {
        this.servicientity = servicientity;
        this.mapperCategoria=mapperCategoria;
    }


    public CategoriaDto crearCat(CategoriaDto categoria) {

       Categoria categoriaEntity= servicientity.CrearCategoria(mapperCategoria.CategoriaDtoToCategoria(categoria));
            if(categoria.getNombre()==null || categoria.getNombre().length()>50){
             
            }
        return mapperCategoria.CategoriatoToCategoriaDto(categoriaEntity);
    }
}
