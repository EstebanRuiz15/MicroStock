package com.microstock.apistock.domain.services;

import java.util.Optional;

import com.microstock.apistock.domain.exception.excepciones_categoria.ErrorException;
import com.microstock.apistock.domain.interfaces.IArticleRepositoryPort;
import com.microstock.apistock.domain.interfaces.IArticleService;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;

public class ServiceArticleImpl implements IArticleService {

    private final IArticleRepositoryPort repository;
    
    public ServiceArticleImpl(IArticleRepositoryPort repository) {
        
        this.repository = repository;
    }

    @Override
    public void createArticle(Article article) {
        Optional <ArticleEntity> exist= repository.findByNombreIgnoreCase(article.getName().trim());
            if(exist.isPresent()){
            throw new ErrorException(ConstantsDomain.NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        repository.saveArticle(article);
    }
    
}
