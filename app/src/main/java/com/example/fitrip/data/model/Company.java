package com.example.fitrip.data.model;

public class Company {
    private Long id;
    private Long usuarioId;
    private String nombreEmpresa;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String emailContacto;
    private String createdAt;

    public Company() {
    }

    public Company(Long id, Long usuarioId, String nombreEmpresa, String descripcion, String direccion,
                   String telefono, String emailContacto, String createdAt) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombreEmpresa = nombreEmpresa;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
