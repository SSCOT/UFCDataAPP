package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media;

import com.google.gson.annotations.SerializedName;

public class Foto {
    private int id;
    @SerializedName("thumbnail_path")
    private String thumbnail;
    @SerializedName("path")
    private String imagen;
    @SerializedName("caption")
    private String descripcion;
    @SerializedName("gallery_order")
    private int posicion;

    public Foto(String thumbnail, String imagen, String descripcion, int posicion, String titulo) {
        this.thumbnail = thumbnail;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.posicion = posicion;
    }

    public int getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}