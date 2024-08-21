package com.microstock.apistock.infraestructura.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.microstock.apistock.aplicacion.mappers.MapperCategoria;
import com.microstock.apistock.aplicacion.servicios.ServicioCategoria;
import com.microstock.apistock.dominio.interfaces.ICategoriaRepository;
import com.microstock.apistock.dominio.servicios.ServicioCategoriaImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Configu {

    @Bean
    public ServicioCategoriaImpl getServicioCategoriaPost(ICategoriaRepository categoriaRepository){
        return new ServicioCategoriaImpl(categoriaRepository);         
    }

    @Bean
    @Primary
    public ServicioCategoria getServicioPostCategoria(ServicioCategoriaImpl servicientity, MapperCategoria mapperCategoria){
        return new ServicioCategoria(servicientity,mapperCategoria);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio Stock")
                        .version("1.0.0")
                        .description("Permitira obtener todo lo necesario para acceder al stock del Ecommerce"));
    }

   
}
