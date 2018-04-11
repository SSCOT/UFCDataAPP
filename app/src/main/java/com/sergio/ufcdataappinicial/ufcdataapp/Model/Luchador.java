package com.sergio.ufcdataappinicial.ufcdataapp.Model;

import com.google.gson.annotations.SerializedName;

public class Luchador {

    private int id;
    @SerializedName("first_name")
    private String nombre;
    @SerializedName("last_name")
    private String apellido;
    @SerializedName("nickname")
    private String nick;

    @SerializedName("weight_class")
    private String categoria;
    @SerializedName("pound_for_pound_rank")
    private String posLibraPorLibra;
    @SerializedName("title_holder")
    private Boolean campeon;
    @SerializedName("fighter_status")
    private String situacionProfesional;

    private int wins;
    private int losses;
    private int draws;

    @SerializedName("belt_thumbnail")
    private String imgCinturon;
    @SerializedName("left_full_body_image")
    private String imgCuerpoIzquierda;
    @SerializedName("right_full_body_image")
    private String imgCuerpoDerecha;
    @SerializedName("profile_image")
    private String imgPerfil;

    public Luchador(int id, String nombre, String apellido, String nick, String categoria, String posLibraPorLibra, Boolean campeon, String situacionProfesional, int wins, int losses, int draws, String imgCinturon, String imgCuerpoIzquierda, String imgCuerpoDerecha, String imgPerfil) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nick = nick;
        this.categoria = categoria;
        this.posLibraPorLibra = posLibraPorLibra;
        this.campeon = campeon;
        this.situacionProfesional = situacionProfesional;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.imgCinturon = imgCinturon;
        this.imgCuerpoIzquierda = imgCuerpoIzquierda;
        this.imgCuerpoDerecha = imgCuerpoDerecha;
        this.imgPerfil = imgPerfil;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPosLibraPorLibra() {
        return posLibraPorLibra;
    }

    public void setPosLibraPorLibra(String posLibraPorLibra) {
        this.posLibraPorLibra = posLibraPorLibra;
    }

    public Boolean getCampeon() {
        return campeon;
    }

    public void setCampeon(Boolean campeon) {
        this.campeon = campeon;
    }

    public String getSituacionProfesional() {
        return situacionProfesional;
    }

    public void setSituacionProfesional(String situacionProfesional) {
        this.situacionProfesional = situacionProfesional;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getImgCinturon() {
        return imgCinturon;
    }

    public void setImgCinturon(String imgCinturon) {
        this.imgCinturon = imgCinturon;
    }

    public String getImgCuerpoIzquierda() {
        return imgCuerpoIzquierda;
    }

    public void setImgCuerpoIzquierda(String imgCuerpoIzquierda) {
        this.imgCuerpoIzquierda = imgCuerpoIzquierda;
    }

    public String getImgCuerpoDerecha() {
        return imgCuerpoDerecha;
    }

    public void setImgCuerpoDerecha(String imgCuerpoDerecha) {
        this.imgCuerpoDerecha = imgCuerpoDerecha;
    }

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }
}
