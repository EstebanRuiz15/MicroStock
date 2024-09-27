package com.microstock.apistock.domain.model;

import java.util.List;

public class Article {

    private  Integer id;
    private  String name;
    private  String description;
    private  Double price;
    private  Integer quantity;
    private  Brand brand;
    private  List <Category> categories;

    public Article(){
        
    }

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
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    
   


    
    

    
}
