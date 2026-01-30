package com.example.fitrip.data.model;

public class ActividadImagen {
    private Long id;
    private Long actividadId;
    private String url;
    private Integer orden;

    public ActividadImagen() {
    }

    public ActividadImagen(Long id, Long actividadId, String url, Integer orden) {
        this.id = id;
        this.actividadId = actividadId;
        this.url = url;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
