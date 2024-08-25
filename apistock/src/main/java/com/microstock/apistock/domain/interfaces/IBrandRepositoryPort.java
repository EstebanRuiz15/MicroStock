package com.microstock.apistock.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.microstock.apistock.domain.model.Brand;
public interface IBrandRepositoryPort {

    void saveBrand(Brand brand);
    Optional<Brand> findByNombreIgnoreCase(String nombre);
    List<Brand> findByBrand(String orden);
}
