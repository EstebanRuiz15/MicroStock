package com.microstock.apistock.domain.services;

import java.util.List;
import java.util.Optional;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorCategory;
import com.microstock.apistock.domain.interfaces.ICategoryRepositoryPort;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.ConstantsDomain;

public class ServiceCategoryImpl implements ICategoryService{

    private final ICategoryRepositoryPort repositorio;

    public ServiceCategoryImpl(ICategoryRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void createCategory(Category category) {
        Optional <Category> exietente= repositorio.findByNombreIgnoreCase(category.getName().trim());
            if(exietente.isPresent()){
            throw new ErrorCategory(ConstantsDomain.NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        
            if(category.getName().trim().length()>ConstantsDomain.MAX_NAME_CHARACTERS){
                throw new ErrorCategory(ConstantsDomain.NAME_MAX_CHARACTERS_EXCEPTION_MESSAGE);
             
            }
            if(category.getName().trim().length()<ConstantsDomain.NOT_NULL){
                throw new ErrorCategory(ConstantsDomain.NAME_NOT_NULL_EXCEPTION_MESSAGE);
             
            }
            if(category.getDescription().trim().length()>ConstantsDomain.MAX_DESCRIPTION_CHARACTERS){
                throw new ErrorCategory(ConstantsDomain.DESCRIPTION_MAX_CHARACTERS_EXCEPTION_MESSAGE);
             
            }
            if(category.getDescription().trim().length()<ConstantsDomain.NOT_NULL){
                throw new ErrorCategory(ConstantsDomain.DESCRIPTION_NOT_NULL_EXCEPTION_MESSAGE);
            }

            
         repositorio.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategory(Integer page, Integer size) {
     
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategoria'");
    }

   
}