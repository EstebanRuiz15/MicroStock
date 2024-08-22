package com.microstock.apistock.dominio.modelo;

public class Categoria {
    private final long id;
    private String nombre;
    private String descripcion;

    public Categoria(long id, String nombre, String descripcion) {
        this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
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
