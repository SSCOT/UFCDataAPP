package com.sergio.ufcdataappinicial.ufcdataapp.Model;

import com.google.gson.annotations.SerializedName;

public class Noticia {

    private int id;

    @SerializedName("title")
    private String titulo;
    @SerializedName("article_date")
    private String fecha;
    @SerializedName("created")
    private String fechaCreacion;
    @SerializedName("last_modified")
    private String fechaModificacion;
    @SerializedName("thumbnail")
    private String img;
    @SerializedName("author")
    private String autor;

    private String url;

    public Noticia(int id, String titulo, String fecha, String fechaCreacion, String fechaModificacion, String img, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.img = img;
        this.autor = autor;

        // TODO extraer string a archivos externos
        this.url = "http://ufc-data-api.ufc.com/api/v3/iphone/5/news/" + this.id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}

