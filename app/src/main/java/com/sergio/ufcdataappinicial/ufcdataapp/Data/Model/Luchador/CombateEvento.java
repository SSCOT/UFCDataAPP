package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CombateEvento implements Serializable {
    @SerializedName("Name")
    private String nombre;
    @SerializedName("Date")
    private String fecha;

    public CombateEvento(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
