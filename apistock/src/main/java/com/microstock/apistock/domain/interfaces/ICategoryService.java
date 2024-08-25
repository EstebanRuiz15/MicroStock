package com.microstock.apistock.domain.interfaces;


import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.PaginCategory;
public interface ICategoryService {

     void createCategory(Category category);
     PaginCategory getAllCategory(Integer page, Integer size, String orden);
} 
