package com.microstock.apistock.domain.services;

import java.util.List;
import java.util.Optional;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorCategory;
import com.microstock.apistock.domain.interfaces.ICategoryRepositoryPort;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.domain.util.PaginCategory;

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
    public PaginCategory getAllCategory(Integer page, Integer size, String orden) {
            
            if(!orden.trim().equalsIgnoreCase(ConstantsDomain.ASC) && !orden.trim().equalsIgnoreCase(ConstantsDomain.DESC)){
                throw new ErrorCategory(ConstantsDomain.ORDEN_DIFERENT_ASC_OR_DESC_EXCEPTION_MESSAGE);
            }

            if(page<ConstantsDomain.ZERO){
                throw new ErrorCategory(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE);
            }

            if(size<=ConstantsDomain.ZERO){
                throw new ErrorCategory(ConstantsDomain.SIZE_MIN_CHARACTER_EXCEPTION_MESSAGE);
            }

            List<Category> allCategory = repositorio.findByCategorias(orden.trim());
            
            // Calcular índices
            Integer totalCategorys = allCategory.size();
            Integer from = Math.min(page * size, totalCategorys);
            Integer to = Math.min((page + ConstantsDomain.ONE) * size, totalCategorys);
            
            // Obtener productos de la página actual
            List<Category> categorypage = allCategory.subList(from, to);
            
            // Calcular total de páginas
            Integer totalPage = (int) Math.ceil((double) totalCategorys / size);

            if(categorypage.isEmpty()){
               throw new ErrorCategory(ConstantsDomain.NO_CATEGORIES_FOUND_EXCEPTION_MESSAGE);
            }
            
            // Construir la respuesta
            return new PaginCategory(
            categorypage,
            page,
            size,
            totalPage,
            totalCategorys);

    }
}