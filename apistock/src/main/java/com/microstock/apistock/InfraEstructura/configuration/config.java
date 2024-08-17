package com.microstock.apistock.InfraEstructura.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microstock.apistock.Aplicacion.mapper.MapperCategoria;
import com.microstock.apistock.Aplicacion.servicios.ServicioPostCategoria;
import com.microstock.apistock.Dominio.servicios.ServicioCategoriaPost;
import com.microstock.apistock.InfraEstructura.Interfaces.ICategoriaRepository;

@Configuration
public class config {  
    @Bean
    public ServicioCategoriaPost getServicioCategoriaPost(ICategoriaRepository categoriaRepository){
        return new ServicioCategoriaPost(categoriaRepository);         
    }

    @Bean
    public ServicioPostCategoria getServicioPostCategoria(ServicioCategoriaPost servicientity, MapperCategoria mapperCategoria){
        return new ServicioPostCategoria(servicientity,mapperCategoria);
    }
}
