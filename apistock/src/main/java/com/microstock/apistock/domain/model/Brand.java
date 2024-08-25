package com.microstock.apistock.domain.model;

public class Brand {

    private final Integer id;
    private final String name;
    private final String description;

    
    public Brand(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    
    
}
