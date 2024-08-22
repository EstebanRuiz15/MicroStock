package com.microstock.apistock.infraestructura.entrada.dtos.peticion;

public class CategoriaDtoAgre {
    
    private String nombre="";
    private String descripcion="";
  
    public CategoriaDtoAgre(){
    }

    public CategoriaDtoAgre(String nombre, String descripcion) {
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
