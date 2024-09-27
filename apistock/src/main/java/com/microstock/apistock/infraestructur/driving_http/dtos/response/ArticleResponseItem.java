package com.microstock.apistock.infraestructur.driving_http.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleResponseItem {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer quantityStock;
    private Integer quantityInCart;
    private String brandName;
    private List<CategoryItemResponse> categories;
}
