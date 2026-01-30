package com.example.fitrip.data.model;

public class ActividadExterior {
    private Long actividadId;
    private OutdoorActivityType tipo;
    private String ubicacion;
    private Double latitud;
    private Double longitud;
    private Integer altitud;
    private Double distanciaKm;
    private Integer duracionMin;
    private DifficultyLevel dificultad;
    private Double puntoInicioLat;
    private Double puntoInicioLng;
    private Double puntoFinLat;
    private Double puntoFinLng;
    private String temporadaRecomendada;
    private String equipoRecomendado;

    public ActividadExterior() {
    }

    public ActividadExterior(Long actividadId, OutdoorActivityType tipo, String ubicacion, Double latitud,
                             Double longitud, Integer altitud, Double distanciaKm, Integer duracionMin,
                             DifficultyLevel dificultad, Double puntoInicioLat, Double puntoInicioLng,
                             Double puntoFinLat, Double puntoFinLng, String temporadaRecomendada,
                             String equipoRecomendado) {
        this.actividadId = actividadId;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
        this.distanciaKm = distanciaKm;
        this.duracionMin = duracionMin;
        this.dificultad = dificultad;
        this.puntoInicioLat = puntoInicioLat;
        this.puntoInicioLng = puntoInicioLng;
        this.puntoFinLat = puntoFinLat;
        this.puntoFinLng = puntoFinLng;
        this.temporadaRecomendada = temporadaRecomendada;
        this.equipoRecomendado = equipoRecomendado;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public OutdoorActivityType getTipo() {
        return tipo;
    }

    public void setTipo(OutdoorActivityType tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public Integer getAltitud() {
        return altitud;
    }

    public void setAltitud(Integer altitud) {
        this.altitud = altitud;
    }

    public Double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(Double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Integer getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }

    public DifficultyLevel getDificultad() {
        return dificultad;
    }

    public void setDificultad(DifficultyLevel dificultad) {
        this.dificultad = dificultad;
    }

    public Double getPuntoInicioLat() {
        return puntoInicioLat;
    }

    public void setPuntoInicioLat(Double puntoInicioLat) {
        this.puntoInicioLat = puntoInicioLat;
    }

    public Double getPuntoInicioLng() {
        return puntoInicioLng;
    }

    public void setPuntoInicioLng(Double puntoInicioLng) {
        this.puntoInicioLng = puntoInicioLng;
    }

    public Double getPuntoFinLat() {
        return puntoFinLat;
    }

    public void setPuntoFinLat(Double puntoFinLat) {
        this.puntoFinLat = puntoFinLat;
    }

    public Double getPuntoFinLng() {
        return puntoFinLng;
    }

    public void setPuntoFinLng(Double puntoFinLng) {
        this.puntoFinLng = puntoFinLng;
    }

    public String getTemporadaRecomendada() {
        return temporadaRecomendada;
    }

    public void setTemporadaRecomendada(String temporadaRecomendada) {
        this.temporadaRecomendada = temporadaRecomendada;
    }

    public String getEquipoRecomendado() {
        return equipoRecomendado;
    }

    public void setEquipoRecomendado(String equipoRecomendado) {
        this.equipoRecomendado = equipoRecomendado;
    }
}
