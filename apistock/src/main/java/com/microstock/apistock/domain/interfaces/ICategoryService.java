package com.microstock.apistock.domain.interfaces;

import java.util.List;

import com.microstock.apistock.domain.model.Category;
public interface ICategoryService {

     void createCategory(Category category);
     List<Category> getAllCategory(Integer page, Integer size);
} 
