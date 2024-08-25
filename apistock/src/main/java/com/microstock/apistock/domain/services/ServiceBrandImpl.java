package com.microstock.apistock.domain.services;

import java.util.Optional;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorException;
import com.microstock.apistock.domain.interfaces.IBrandRepositoryPort;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.util.ConstantsDomain;

public class ServiceBrandImpl implements IBrandService {
    
    private final IBrandRepositoryPort repository;

    public ServiceBrandImpl(IBrandRepositoryPort repository){
        this.repository=repository;
    }

    @Override
    public void createBrand(Brand brand) {
         Optional <Brand> exist= repository.findByNombreIgnoreCase(brand.getName().trim());
            if(exist.isPresent()){
            throw new ErrorException(ConstantsDomain.NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }

            if(brand.getName().trim().length()>ConstantsDomain.MAX_NAME_CHARACTERS){
                throw new ErrorException(ConstantsDomain.NAME_MAX_CHARACTERS_EXCEPTION_MESSAGE);
             
            }
            if(brand.getName().trim().length()<ConstantsDomain.NOT_NULL){
                throw new ErrorException(ConstantsDomain.NAME_NOT_NULL_EXCEPTION_MESSAGE);
             
            }
            if(brand.getDescription().trim().length()>ConstantsDomain.MAX_DESCRIPTION_BRAND_CHARACTERS){
                throw new ErrorException(ConstantsDomain.DESCRIPTION_MAX_CHARACTERS_BRAND_EXCEPTION_MESSAGE);
             
            }
            if(brand.getDescription().trim().length()<ConstantsDomain.NOT_NULL){
                throw new ErrorException(ConstantsDomain.DESCRIPTION_NOT_NULL_EXCEPTION_MESSAGE);
            }

        
        repository.saveBrand(brand);
}
}