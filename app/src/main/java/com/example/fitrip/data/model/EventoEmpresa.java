package com.example.fitrip.data.model;

public class EventoEmpresa {
    private Long id;
    private Long empresaId;
    private Long actividadId;
    private Long usuarioId;
    private TipoEvento tipo;
    private String createdAt;

    public EventoEmpresa() {
    }

    public EventoEmpresa(Long id, Long empresaId, Long actividadId, Long usuarioId, TipoEvento tipo,
                         String createdAt) {
        this.id = id;
        this.empresaId = empresaId;
        this.actividadId = actividadId;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
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

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
