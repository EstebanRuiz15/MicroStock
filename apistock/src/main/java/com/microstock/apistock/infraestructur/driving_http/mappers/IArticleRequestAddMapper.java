package com.microstock.apistock.infraestructur.driving_http.mappers;

import org.mapstruct.Mapper;

import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.ArticleDtoAdd;

@Mapper(componentModel = "spring")
public interface IArticleRequestAddMapper {
    Article toArticle(ArticleDtoAdd articleDtoAdd);
    
}
