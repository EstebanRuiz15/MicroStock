package com.microstock.apistock.infraestructur.driving_http.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.infraestructur.driving_http.dtos.response.CategoryItemResponse;

import java.util.List;
@Mapper(componentModel = ("spring"))
public interface CategoryItemMapper {
    CategoryItemMapper INSTANCE = Mappers.getMapper(CategoryItemMapper.class);
    List<CategoryItemResponse> toNames(List<Category> category);
}