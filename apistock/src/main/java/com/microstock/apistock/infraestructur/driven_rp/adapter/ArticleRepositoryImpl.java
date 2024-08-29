package com.microstock.apistock.infraestructur.driven_rp.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microstock.apistock.domain.interfaces.IArticleRepositoryPort;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IArticleToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.ArticleRespositoryJpa;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleRepositoryImpl implements IArticleRepositoryPort{
    private final ArticleRespositoryJpa respositoryJpa;
    private final IBrandService serviceBrand;
    private final IArticleToEntityMapper mapper;
    private final ICategoryService service;
    
    @Override
    public void saveArticle(Article article) {
         respositoryJpa.save(mapper.toArticleEntity(article, service,serviceBrand ));
    }

    
    @Override
    public Optional<ArticleEntity> findByNombreIgnoreCase(String name) {
      return respositoryJpa.findByNameIgnoreCase(name);
    }
    
}
