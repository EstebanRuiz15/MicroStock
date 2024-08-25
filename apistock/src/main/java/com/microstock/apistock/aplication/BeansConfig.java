package com.microstock.apistock.aplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microstock.apistock.domain.interfaces.IBrandRepositoryPort;
import com.microstock.apistock.domain.interfaces.ICategoryRepositoryPort;
import com.microstock.apistock.domain.services.ServiceBrandImpl;
import com.microstock.apistock.domain.services.ServiceCategoryImpl;
import com.microstock.apistock.infraestructur.driven_rp.adapter.CategoryRepositoryImpl;
import com.microstock.apistock.infraestructur.driven_rp.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.CategoriaRepositoryJpa;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class BeansConfig {

    @Bean
    public ServiceCategoryImpl getServiceCategoryImpl(ICategoryRepositoryPort categoriaRepository){
        return new ServiceCategoryImpl(categoriaRepository);         
    }

    @Bean
    public CategoryRepositoryImpl getCategoryRepositoryImpl( CategoriaRepositoryJpa jpaRepository,ICategoriaToEntitymapper mapperEntity){
        return new CategoryRepositoryImpl(jpaRepository, mapperEntity);
    }

    @Bean
    public ServiceBrandImpl getServiceBrandImpl(IBrandRepositoryPort iBrandRepositoryPort){
        return new ServiceBrandImpl(iBrandRepositoryPort);
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservice Stock")
                        .version("1.0.0")
                        .description("allows you to obtain information about the Amazon ecommerce stock"));
    }

   
}
