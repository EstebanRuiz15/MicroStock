package com.microstock.apistock.infraestructura.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microstock.apistock.aplicacion.mappers.MapperCategoria;
import com.microstock.apistock.aplicacion.servicios.ServicioPostCategoria;
import com.microstock.apistock.dominio.servicios.ServicioCategoriaPost;
import com.microstock.apistock.infraestructura.interfaces.ICategoriaRepository;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Configu {

    @Bean
    public ServicioCategoriaPost getServicioCategoriaPost(ICategoriaRepository categoriaRepository){
        return new ServicioCategoriaPost(categoriaRepository);         
    }

    @Bean
    public ServicioPostCategoria getServicioPostCategoria(ServicioCategoriaPost servicientity, MapperCategoria mapperCategoria, ICategoriaRepository iCategoriaRepository){
        return new ServicioPostCategoria(servicientity,mapperCategoria, iCategoriaRepository);
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
