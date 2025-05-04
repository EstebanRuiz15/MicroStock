package com.microstock.apistock.infraestructur.driving_http.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.microstock.apistock.domain.exception.ErrorException;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IBrandToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.ArticleDtoAdd;
@Mapper(componentModel = "spring", uses = {})
public interface  IArticleRequestAddMapper {
     
   @Mapping(source = "brandId", target = "brand", qualifiedByName = "mapIdBrandidToBrand")
   @Mapping(source = "categoriesId", target = "categories", qualifiedByName = "mapCategoryIdsToCategory")
   Article toArticleModel(ArticleDtoAdd articleDtoAdd, @Context ICategoryService iCategoryService, @Context IBrandService iBrandService,
                         @Context IBrandToEntityMapper brandMapper, @Context ICategoriaToEntitymapper categoryMapper);


   @Named("mapIdBrandidToBrand")
   default Brand mapIdBrandidToBrand(Integer brandId, @Context IBrandService iBrandService,@Context IBrandToEntityMapper brandMapper) {
       if (brandId == null) {
           return null;
       }
       return iBrandService.findById(brandId)
       .map(brandMapper::toBrand) 
       .orElseThrow(() -> new ErrorException(ConstantsDomain.NO_BRAND_EXCEPTION_MESSAGE + ", " + brandId));
   }

   @Named("mapCategoryIdsToCategory")
   default List<Category> mapCategoryIdsToCategory(List<Integer> categoriesId, @Context ICategoryService iCategoryService, @Context ICategoriaToEntitymapper categoryMapper) {
    if (categoriesId == null) {
        return null;
    }

    return categoriesId.stream()
            .map(id -> iCategoryService.findById(id)
                    .map(categoryMapper::toCategory) 
                    .orElseThrow(() -> new ErrorException(ConstantsDomain.NO_CATEGORY_EXCEPTION_MESSAGE + ", " + id)))
            .collect(Collectors.toList());
   }

   
    
}
