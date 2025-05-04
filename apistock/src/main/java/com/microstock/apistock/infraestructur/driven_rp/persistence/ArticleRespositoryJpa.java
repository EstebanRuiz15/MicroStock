package com.microstock.apistock.infraestructur.driven_rp.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;
import java.util.List;

@Repository
public interface ArticleRespositoryJpa extends JpaRepository<ArticleEntity,Integer> {
     Optional<ArticleEntity> findByNameIgnoreCase(String name);
     @SuppressWarnings("null")
     Optional<ArticleEntity> findById(Integer id);
     @Query("SELECT a FROM ArticleEntity a JOIN FETCH a.categories WHERE a.id IN :ids")
      List<ArticleEntity> findAllById(List<Integer> ids);
     
}
