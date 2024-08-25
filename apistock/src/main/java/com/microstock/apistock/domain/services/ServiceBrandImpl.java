package com.microstock.apistock.domain.services;

import java.util.List;
import java.util.Optional;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorException;
import com.microstock.apistock.domain.interfaces.IBrandRepositoryPort;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.domain.util.PaginBrand;

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

    @Override
    public PaginBrand getAllBrand(Integer page, Integer size, String orden) {
         if(!orden.trim().equalsIgnoreCase(ConstantsDomain.ASC) && !orden.trim().equalsIgnoreCase(ConstantsDomain.DESC)){
                throw new ErrorException(ConstantsDomain.ORDEN_DIFERENT_ASC_OR_DESC_EXCEPTION_MESSAGE);
            }

            if(page<ConstantsDomain.ZERO){
                throw new ErrorException(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE);
            }

            if(size<=ConstantsDomain.ZERO){
                throw new ErrorException(ConstantsDomain.SIZE_MIN_CHARACTER_EXCEPTION_MESSAGE);
            }

            List<Brand> allBrands = repository.findByBrand(orden.trim());
            if(allBrands.isEmpty()){
                throw new ErrorException(ConstantsDomain.NO_DATA_BRANDS_EXCEPTION_MESSAGE);
            }
            
            // Calculate index
            Integer totalBrands = allBrands.size();
            Integer from = Math.min(page * size, totalBrands);
            Integer to = Math.min((page + ConstantsDomain.ONE) * size, totalBrands);
            
            // get categories of the current page
            List<Brand> brandpage = allBrands.subList(from, to);
            
            // calculate total of pages
            Integer totalPage = (int) Math.ceil((double) totalBrands / size);

            if(brandpage.isEmpty()){
               throw new ErrorException(ConstantsDomain.NO_BRANDS_FOUND_EXCEPTION_MESSAGE+
                                        ConstantsDomain.TOTAL_PAGES+totalPage);
            }
            
            return new PaginBrand(
            brandpage,
            page,
            size,
            totalPage,
            totalBrands);

    }
}