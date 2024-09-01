package com.microstock.apistock.domain.interfaces;

import java.util.Optional;

import com.microstock.apistock.domain.model.Article;
import java.util.List;

public interface IArticleRepositoryPort {
    void saveArticle(Article article);
    Optional<Article> findByNombreIgnoreCase(String name);
    List<Article> finByArticle();
}
