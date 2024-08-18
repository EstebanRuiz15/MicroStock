package com.microstock.apistock.aplicacion.dtos;

public class CategoriaDto {
    
    private String nombre="";
    private String descripcion="";
  
    public CategoriaDto(){
    }

    public CategoriaDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
