package com.microstock.apistock.infraestructur.driven_rp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorException;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;
@Mapper(componentModel = "spring", uses = {})
public interface  IArticleToEntityMapper {
     
   @Mapping(source = "brandId", target = "brand", qualifiedByName = "mapIdBrandToBrandEntity")
   @Mapping(source = "categoriesId", target = "categories", qualifiedByName = "mapCategoryIdsToCategoryEntities")
   ArticleEntity toArticleEntity(Article article, @Context ICategoryService iCategoryService, @Context IBrandService iBrandService);


   @Named("mapIdBrandToBrandEntity")
   default BrandEntity mapIdBrandToBrandEntity(Integer brandId, @Context IBrandService iBrandService) {
       if (brandId == null) {
           return null;
       }
       return iBrandService.findById(brandId)
               .orElseThrow(() -> new ErrorException(ConstantsDomain.NO_BRAND_EXCEPTION_MESSAGE+", "+brandId));
   }

   @Named("mapCategoryIdsToCategoryEntities")
   default List<CategoryEntity> mapCategoryIdsToCategoryEntities(List<Integer> categoriesId, @Context ICategoryService iCategoryService) {
       if (categoriesId == null) {
           return null;
       }
       return categoriesId.stream()
               .map(id -> iCategoryService.findById(id).orElseThrow(() -> new ErrorException(ConstantsDomain.NO_CATEGORY_EXCEPTION_MESSAGE+", "+id)))
               .collect(Collectors.toList());
   }

   
    
}
