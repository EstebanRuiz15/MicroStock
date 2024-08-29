package com.microstock.apistock.domain.interfaces;


import java.util.Optional;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.PaginCategory;
import com.microstock.apistock.infraestructur.driven_rp.entity.CategoryEntity;
public interface ICategoryService {

     void createCategory(Category category);
     PaginCategory getAllCategory(Integer page, Integer size, String orden);
     Optional<CategoryEntity> findById(Integer id);
} 
