package com.microstock.apistock.domain.model;

import java.util.List;

public class Article {

    private final Integer id;
    private final String name;
    private final String description;
    private final Double price;
    private final Integer quantity;
    private final Brand brand;
    private final List <Category> categories;
    public Article(Integer id, String name, String description, Double price, Integer quantity, Brand brand,
            List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.categories = categories;
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
    public Brand getBrand() {
        return brand;
    }
    public List<Category> getCategories() {
        return categories;
    }
    
    
   


    
    

    
}
