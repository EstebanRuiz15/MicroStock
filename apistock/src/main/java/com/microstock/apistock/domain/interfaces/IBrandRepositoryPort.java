package com.microstock.apistock.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;
public interface IBrandRepositoryPort {

    void saveBrand(Brand brand);
    Optional<Brand> findByNombreIgnoreCase(String nombre);
    List<Brand> findByBrand(String orden);
    Optional<BrandEntity> findById(Integer id);
}
