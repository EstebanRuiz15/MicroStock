package com.microstock.apistock.domain.interfaces;

import java.util.Optional;

import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;

public interface IArticleRepositoryPort {
    void saveArticle(Article article);
    Optional<ArticleEntity> findByNombreIgnoreCase(String name);
}
