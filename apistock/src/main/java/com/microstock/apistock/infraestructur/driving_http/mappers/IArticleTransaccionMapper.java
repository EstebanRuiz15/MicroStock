package com.microstock.apistock.infraestructur.driving_http.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.infraestructur.driving_http.dtos.response.ArticlesTransaccion;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleTransaccionMapper {

    @Mapping(target = "totalArticle", expression = "java(article.getPrice() * article.getQuantity())")
    ArticlesTransaccion toArticlesTransaccion(Article article);

    List<ArticlesTransaccion> toArticlesTransaccionList(List<Article> articles);
}

