package com.microstock.apistock.infraestructur.driving_http.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.infraestructur.driving_http.dtos.response.ArticleResponseItem;
import java.util.List;

@Mapper(componentModel="spring", uses=CategoryItemMapper.class)
public interface ArticleITemMapper {
    ArticleITemMapper INSTANCE = Mappers.getMapper(ArticleITemMapper.class);

    @Mapping(target = "quantityStock", source = "quantity") 
    @Mapping(target = "quantityInCart", ignore = true)
    @Mapping(source = "brand.name", target = "brandName") 
    ArticleResponseItem toDto(Article article);

    List<ArticleResponseItem> toDtoList(List<Article> articles);
}

