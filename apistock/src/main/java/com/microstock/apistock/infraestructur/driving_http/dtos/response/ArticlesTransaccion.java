package com.microstock.apistock.infraestructur.driving_http.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticlesTransaccion {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Double totalArticle;
    private Integer quantity;
}
