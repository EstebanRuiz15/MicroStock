package com.microstock.apistock.domain.model;

import java.util.List;

public class Article {

    private final Integer id;
    private final String name;
    private final String description;
    private final Double price;
    private final Integer quantity;
    private final Integer brandId;
    private final List <Integer> categoriesId;
    public Article(Integer id, String name, String description, Double price, Integer quantity, Integer brandId,
            List<Integer> categoriesId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.brandId = brandId;
        this.categoriesId = categoriesId;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Integer getBrandId() {
        return brandId;
    }
    public List<Integer> getCategoriesId() {
        return categoriesId;
    }


    
    

    
}
