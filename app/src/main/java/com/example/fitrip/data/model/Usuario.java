package com.example.fitrip.data.model;

public class Usuario {
    private Long id;
    private String firebaseUid;
    private String nombre;
    private String email;
    private String telefono;
    private String fotoUrl;
    private RolUsuario rol;
    private String createdAt;

    public Usuario() {
    }

    public Usuario(Long id, String firebaseUid, String nombre, String email, String telefono, String fotoUrl,
                   RolUsuario rol, String createdAt) {
        this.id = id;
        this.firebaseUid = firebaseUid;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fotoUrl = fotoUrl;
        this.rol = rol;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
