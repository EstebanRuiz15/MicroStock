package com.microstock.apistock.infraestructur.driven_rp.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microstock.apistock.infraestructur.driven_rp.entity.ArticleEntity;

@Repository
public interface ArticleRespositoryJpa extends JpaRepository<ArticleEntity,Integer> {
     Optional<ArticleEntity> findByNameIgnoreCase(String name);
}
