package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CombateOponente implements Serializable {
    @SerializedName("FirstName")
    private String nombre;
    @SerializedName("LastName")
    private String apellido;
    @SerializedName("profile_image")
    private String img;

    public CombateOponente(String nombre, String apellido, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

