package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento;

import com.google.gson.annotations.SerializedName;

public class Resultado {

    @SerializedName("Method")
    private String metodoFinalizacion;
    @SerializedName("EndingRound")
    private String roundFinalizacion;
    @SerializedName("FightOfTheNight")
    private Boolean esPeleaDeLaNoche;

    @SerializedName("Scores")
    private Puntuacion[] puntuaciones;

    public Resultado(String metodoFinalizacion, String roundFinalizacion, Boolean esPeleaDeLaNoche, Puntuacion[] puntuaciones) {
        this.metodoFinalizacion = metodoFinalizacion;
        this.roundFinalizacion = roundFinalizacion;
        this.esPeleaDeLaNoche = esPeleaDeLaNoche;
        this.puntuaciones = puntuaciones;
    }

    public String getMetodoFinalizacion() {
        return metodoFinalizacion;
    }

    public void setMetodoFinalizacion(String metodoFinalizacion) {
        this.metodoFinalizacion = metodoFinalizacion;
    }

    public String getRoundFinalizacion() {
        return roundFinalizacion;
    }

    public void setRoundFinalizacion(String roundFinalizacion) {
        this.roundFinalizacion = roundFinalizacion;
    }

    public Boolean getEsPeleaDeLaNoche() {
        return esPeleaDeLaNoche;
    }

    public void setEsPeleaDeLaNoche(Boolean esPeleaDeLaNoche) {
        this.esPeleaDeLaNoche = esPeleaDeLaNoche;
    }

    public Puntuacion[] getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(Puntuacion[] puntuaciones) {
        this.puntuaciones = puntuaciones;
    }
}
