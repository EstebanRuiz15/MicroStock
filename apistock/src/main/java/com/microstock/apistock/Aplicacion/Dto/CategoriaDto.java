package com.microstock.apistock.Aplicacion.Dto;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;


public class CategoriaDto {
    @Size(max = 50, min = 5, message = "Longitud debe estar entre 5 y 50 caracteres")
    private String nombre;
    private String descripcion;

    
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
