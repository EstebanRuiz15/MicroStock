package com.microstock.apistock.domain.interfaces;

import java.util.Optional;

import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.util.PaginBrand;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;

public interface IBrandService {
    
    void createBrand(Brand brand);
    PaginBrand getAllBrand(Integer page, Integer size, String orden);
    Optional<BrandEntity> findById(Integer id);

}
