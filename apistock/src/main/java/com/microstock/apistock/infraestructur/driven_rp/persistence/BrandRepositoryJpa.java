package com.microstock.apistock.infraestructur.driven_rp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;

@Repository
public interface BrandRepositoryJpa extends JpaRepository<BrandEntity, Integer> {
     Optional<Brand> findByNameIgnoreCase(String name);
    @SuppressWarnings("null")
    List<BrandEntity> findAll(Sort sort);
}
