package com.microstock.apistock.infraestructur.driven_rp.adapter;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microstock.apistock.domain.interfaces.IArticleRepositoryPort;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IArticleToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.ArticleRespositoryJpa;
import com.microstock.apistock.infraestructur.util.ConstantsInfraestructure;

import java.util.List;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleRepositoryImpl implements IArticleRepositoryPort{
    private final ArticleRespositoryJpa respositoryJpa;
    private final IArticleToEntityMapper mapper;
    
    @Override
    public void saveArticle(Article article) {
         respositoryJpa.save(mapper.toArticleEntity(article ));
    }

    
    @Override
    public Optional<Article> findByNombreIgnoreCase(String name) {
      return mapper.toArticle(respositoryJpa.findByNameIgnoreCase(name));
    }


    @Override
    public List<Article> finByArticle() { 
            
        return mapper.toListArticle(respositoryJpa.findAll());
    }


    @Override
    public Optional<Article> findById(Integer id) {
      
      return mapper.toArticle(respositoryJpa.findById(id));
    }


    @Override
    public String saveUpdate(Article article) {
     respositoryJpa.save(mapper.toArticleEntity(article));
     return ConstantsInfraestructure.UPDATE;
    }

}
    

