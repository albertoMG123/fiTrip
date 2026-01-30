package com.example.fitrip.data.model;

public class Activity {
    private Long id;
    private Long empresaId;
    private Long deporteId;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String moneda;
    private String imagenPrincipalUrl;
    private String createdAt;

    public Activity() {
    }

    public Activity(Long id, Long empresaId, Long deporteId, String nombre, String descripcion,
                    Double precio, String moneda, String imagenPrincipalUrl, String createdAt) {
        this.id = id;
        this.empresaId = empresaId;
        this.deporteId = deporteId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.moneda = moneda;
        this.imagenPrincipalUrl = imagenPrincipalUrl;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getDeporteId() {
        return deporteId;
    }

    public void setDeporteId(Long deporteId) {
        this.deporteId = deporteId;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getImagenPrincipalUrl() {
        return imagenPrincipalUrl;
    }

    public void setImagenPrincipalUrl(String imagenPrincipalUrl) {
        this.imagenPrincipalUrl = imagenPrincipalUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
