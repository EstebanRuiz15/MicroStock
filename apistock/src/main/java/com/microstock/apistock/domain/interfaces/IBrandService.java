package com.microstock.apistock.domain.interfaces;

import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.domain.util.PaginBrand;

public interface IBrandService {
    
    void createBrand(Brand brand);
    PaginBrand getAllBrand(Integer page, Integer size, String orden);
}
