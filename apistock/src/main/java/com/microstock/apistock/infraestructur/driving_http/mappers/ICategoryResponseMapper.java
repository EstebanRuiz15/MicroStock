package com.microstock.apistock.infraestructur.driving_http.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driving_http.dtos.response.CategoryResponse;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> toCategoryResponseList(List<Category> category);
}