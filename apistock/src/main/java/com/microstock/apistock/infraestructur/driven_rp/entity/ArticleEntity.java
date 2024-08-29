package com.microstock.apistock.infraestructur.driven_rp.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="articulo")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nombre", nullable = false, unique = true)
    private String name;

    @Column (name="descripcion",nullable=false, unique = true)
    private String description;

    @Column (name= "precio")
    private Double price;

    @Column (name="cantidad")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="id_marca",referencedColumnName = "id")
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(name = "articulo_categoria",
               joinColumns = @JoinColumn(name= "id_articulo"),
               inverseJoinColumns= @JoinColumn(name= "id_categoria")
               )
    private List<CategoryEntity> categories;

    public ArticleEntity(){

    }

    public ArticleEntity(Integer id, String name, String description, Double price, Integer quantity, BrandEntity brand,
            List<CategoryEntity> categories) {
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

    public void setId(Integer id) {
        this.id = id;
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

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
    


    


} 
