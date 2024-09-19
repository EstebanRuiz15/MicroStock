package com.microstock.apistock.domain.interfaces;

import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.util.PaginArticle;

public interface IArticleService {
    void createArticle(Article article);
    PaginArticle getAllArticles(Integer page, Integer size,String orden, String nameOrden);
    String incrementArticles(Integer idArticle,Integer quantity);
    void rollbackArticles (Integer idArticle, Integer quantity);
}
