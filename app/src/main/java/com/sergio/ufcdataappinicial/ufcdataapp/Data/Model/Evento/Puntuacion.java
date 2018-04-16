package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento;

import com.google.gson.annotations.SerializedName;

public class Puntuacion {

    @SerializedName("WinnerScore")
    private String puntuacionGanador;
    @SerializedName("LoserScore")
    private String puntuacionPerdedor;
    @SerializedName("JudgeFirstName")
    private String nombreJuez;
    @SerializedName("JudgeLastName")
    private String apellidoJuez;

    public Puntuacion(String puntuacionGanador, String puntuacionPerdedor, String nombreJuez, String apellidoJuez) {
        this.puntuacionGanador = puntuacionGanador;
        this.puntuacionPerdedor = puntuacionPerdedor;
        this.nombreJuez = nombreJuez;
        this.apellidoJuez = apellidoJuez;
    }

    public String getPuntuacionGanador() {
        return puntuacionGanador;
    }

    public void setPuntuacionGanador(String puntuacionGanador) {
        this.puntuacionGanador = puntuacionGanador;
    }

    public String getPuntuacionPerdedor() {
        return puntuacionPerdedor;
    }

    public void setPuntuacionPerdedor(String puntuacionPerdedor) {
        this.puntuacionPerdedor = puntuacionPerdedor;
    }

    public String getNombreJuez() {
        return nombreJuez;
    }

    public void setNombreJuez(String nombreJuez) {
        this.nombreJuez = nombreJuez;
    }

    public String getApellidoJuez() {
        return apellidoJuez;
    }

    public void setApellidoJuez(String apellidoJuez) {
        this.apellidoJuez = apellidoJuez;
    }
}