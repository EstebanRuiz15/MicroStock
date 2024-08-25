package com.microstock.apistock.infraestructur.driving_http.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryResponse {
   
    private final Long id;
    private final String name;
    private final String description;


}
