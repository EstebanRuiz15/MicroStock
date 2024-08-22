package com.microstock.apistock.aplicacion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.microstock.apistock.dominio.interfaces.ICategoriaRepositoryPort;
import com.microstock.apistock.dominio.servicios.ServicioCategoriaImpl;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class BeansConfig {

    @Bean
    public ServicioCategoriaImpl getServicioCategoriaPost(ICategoriaRepositoryPort categoriaRepository){
        return new ServicioCategoriaImpl(categoriaRepository);         
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
