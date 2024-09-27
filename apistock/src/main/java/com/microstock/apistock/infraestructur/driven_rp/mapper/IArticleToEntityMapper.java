package com.microstock.apistock.infraestructur.driven_rp.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;
@Mapper(componentModel = "spring")

public interface  IArticleToEntityMapper { 
   ArticleEntity toArticleEntity(Article article);
   default Optional<Article> toArticle(Optional<ArticleEntity> articleEntity) {
       return articleEntity.map(this::toArticle); 
   }

   Article toArticle(ArticleEntity articleEntity);

   default List<Article> toListArticle(List<ArticleEntity> articleEntities) {
       return articleEntities == null || articleEntities.isEmpty()
           ? Collections.emptyList() 
           : articleEntities.stream()
                            .map(this::toArticle)
                            .collect(Collectors.toList());
   }
}