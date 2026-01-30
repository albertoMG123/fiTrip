package com.example.fitrip.data.model;

public class Deporte {
    private Long id;
    private String nombre;
    private SportCategory categoria;
    private String createdAt;

    public Deporte() {
    }

    public Deporte(Long id, String nombre, SportCategory categoria, String createdAt) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SportCategory getCategoria() {
        return categoria;
    }

    public void setCategoria(SportCategory categoria) {
        this.categoria = categoria;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
