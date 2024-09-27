package com.microstock.apistock.infraestructur.driving_http.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleResponse {
    private  Integer id;
    private  String name;
    private  String description;
    private  Double price;
    private  Integer quantity;
    private  String brandName;
    private  List<CategoryResponse> categories;
}
