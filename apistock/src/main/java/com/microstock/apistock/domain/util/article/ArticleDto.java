package com.microstock.apistock.domain.util.article;

import java.util.List;

public class ArticleDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String brandName;
    private List<CategoryDto> categories;

    public ArticleDto(Integer id,String name, String description, Double price, Integer quantity, String brandName,
            List<CategoryDto> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.brandName = brandName;
        this.categories = categories;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getbrandName() {
        return brandName;
    }
    public void setbrandName(String brandName) {
        this.brandName = brandName;
    }
    public List<CategoryDto> getCategories() {
        return categories;
    }
    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
    
}
