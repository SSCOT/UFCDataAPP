package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Luchador implements Serializable {

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

    @SerializedName("city_residing")
    private String residenciaCiudad;
    @SerializedName("state_residing")
    private String residenciaEstado;
    @SerializedName("country_residing")
    private String residenciaPais;
    @SerializedName("height_ft")
    private String altura;
    @SerializedName("weight_kg")
    private float peso;
    @SerializedName("ko_tko_wins")
    private int winsKo;
    @SerializedName("submission_wins")
    private int winsSubmission;
    @SerializedName("decision_wins")
    private int winsDecision;
    @SerializedName("strengths")
    private String habilidades;

    public static Hashtable<String, String> countries = new Hashtable<String, String>();

    public Luchador(String nombre, String apellido, String nick, String categoria, String posLibraPorLibra, Boolean campeon, String situacionProfesional, int wins, int losses, int draws, String imgCinturon, String imgCuerpoIzquierda, String imgCuerpoDerecha, String imgPerfil, String residenciaCiudad, String residenciaEstado, String residenciaPais, String altura, float peso, int winsKo, int winsSubmission, int winsDecision, String habilidades) {
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
        this.residenciaCiudad = residenciaCiudad;
        this.residenciaEstado = residenciaEstado;
        this.residenciaPais = residenciaPais;
        this.altura = altura;
        this.peso = peso;
        this.winsKo = winsKo;
        this.winsSubmission = winsSubmission;
        this.winsDecision = winsDecision;
        this.habilidades = habilidades;
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
        return categoria.replace("_"," ");
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

    public String getResidenciaCiudad() {
        return residenciaCiudad;
    }

    public void setResidenciaCiudad(String residenciaCiudad) {
        this.residenciaCiudad = residenciaCiudad;
    }

    public String getResidenciaEstado() {
        return residenciaEstado;
    }

    public void setResidenciaEstado(String residenciaEstado) {
        this.residenciaEstado = residenciaEstado;
    }

    public String getResidenciaPais() {
        return residenciaPais;
    }

    public void setResidenciaPais(String residenciaPais) {
        this.residenciaPais = residenciaPais;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getWinsKo() {
        return winsKo;
    }

    public void setWinsKo(int winsKo) {
        this.winsKo = winsKo;
    }

    public int getWinsSubmission() {
        return winsSubmission;
    }

    public void setWinsSubmission(int winsSubmission) {
        this.winsSubmission = winsSubmission;
    }

    public int getWinsDecision() {
        return winsDecision;
    }

    public void setWinsDecision(int winsDecision) {
        this.winsDecision = winsDecision;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

}
