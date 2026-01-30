package com.example.fitrip.data.model;

import java.util.List;

public class IndoorActivity {
    private Long actividadId;
    private String direccion;
    private Double latitud;
    private Double longitud;
    private String horario;
    private List<String> servicios;
    private Double precioMensual;
    private Double precioSesion;
    private Integer capacidad;

    public IndoorActivity() {
    }

    public IndoorActivity(Long actividadId, String direccion, Double latitud, Double longitud,
                          String horario, List<String> servicios, Double precioMensual,
                          Double precioSesion, Integer capacidad) {
        this.actividadId = actividadId;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario = horario;
        this.servicios = servicios;
        this.precioMensual = precioMensual;
        this.precioSesion = precioSesion;
        this.capacidad = capacidad;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<String> getServicios() {
        return servicios;
    }

    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    public Double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(Double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public Double getPrecioSesion() {
        return precioSesion;
    }

    public void setPrecioSesion(Double precioSesion) {
        this.precioSesion = precioSesion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
