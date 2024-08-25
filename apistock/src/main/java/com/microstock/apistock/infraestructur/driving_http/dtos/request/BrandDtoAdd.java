package com.microstock.apistock.infraestructur.driving_http.dtos.request;


public class BrandDtoAdd {

    private  String name="";
    private   String description="";
  
    public BrandDtoAdd(){
    }

    public BrandDtoAdd(String name, String description) {
        this.name = name;
        this.description = description;
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

}
