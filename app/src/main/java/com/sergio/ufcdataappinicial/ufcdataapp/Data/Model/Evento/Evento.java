package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento;

import com.google.gson.annotations.SerializedName;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;

public class Evento {

    private int id;

    @SerializedName("event_date")
    private String fecha;
    @SerializedName("base_title")
    private String titulo;
    @SerializedName("title_tag_line")
    private String subtitulo;
    @SerializedName("event_status")
    private String estado;
    private String arena;
    @SerializedName("location")
    private String ciudad;
    @SerializedName("latitude")
    private float lat;
    @SerializedName("longitude")
    private float lon;
    @SerializedName("main_event_fighter1_id")
    private int idLuchador1;
    @SerializedName("main_event_fighter2_id")
    private int idLuchador2;
    private Luchador luchador1;
    private Luchador luchador2;

    private Combate[] combates;

    public Evento(int id, String fecha, String titulo, String subtitulo, String estado, String arena, String ciudad, float lat, float lon, int idLuchador1, int idLuchador2) {
        this.id = id;
        this.fecha = fecha;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.estado = estado;
        this.arena = arena;
        this.ciudad = ciudad;
        this.lat = lat;
        this.lon = lon;
        this.idLuchador1 = idLuchador1;
        this.idLuchador2 = idLuchador2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        if (fecha.length() > 9)
            fecha = fecha.substring(0,10);
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public Combate[] getCombates() {
        return combates;
    }

    public int getIdLuchador1() {
        return idLuchador1;
    }

    public void setIdLuchador1(int idLuchador1) {
        this.idLuchador1 = idLuchador1;
    }

    public int getIdLuchador2() {
        return idLuchador2;
    }

    public void setIdLuchador2(int idLuchador2) {
        this.idLuchador2 = idLuchador2;
    }

    public Luchador getLuchador1() {
        return luchador1;
    }

    public void setLuchador1(Luchador luchador1) {
        this.luchador1 = luchador1;
    }

    public Luchador getLuchador2() {
        return luchador2;
    }

    public void setLuchador2(Luchador luchador2) {
        this.luchador2 = luchador2;
    }

    public void setCombates(Combate[] combates) {
        this.combates = combates;
    }
}
