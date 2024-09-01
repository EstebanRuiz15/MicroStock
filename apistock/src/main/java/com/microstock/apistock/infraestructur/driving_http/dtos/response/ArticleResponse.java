package com.microstock.apistock.infraestructur.driving_http.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleResponse {
    private final Integer id;
    private final String name;
    private final String description;
    private final Double price;
    private final Integer quantity;
    private final String brandName;
    private final List<CategoryResponse> categories;

   
}
