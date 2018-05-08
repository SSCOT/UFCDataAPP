package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media;

import com.google.gson.annotations.SerializedName;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import java.io.Serializable;

public class Media implements Serializable {

    private int id;

    @SerializedName("type")
    private String tipo;
    @SerializedName("title")
    private String titulo;
    @SerializedName("description")
    private String descripcion;
    @SerializedName("thumbnail")
    private String img;
    @SerializedName("embedded_type")
    private String tipoEmbebido;
    @SerializedName("embedded_id")
    private String idEmbebido;
    @SerializedName("mobile_stream_url")
    private String urlStream;
    @SerializedName("mobile_video_url")
    private String urlVideo;
    @SerializedName("internal_url")
    private String urlInterna;
    @SerializedName("media_date")
    private String fecha;
    @SerializedName("published_start_date")
    private String fechaInicial;
    @SerializedName("photos")
    private Foto[] fotos;

    public Media(String tipo, String titulo, String descripcion, String img, String tipoEmbebido, String idEmbebido, String urlStream, String urlVideo, String urlInterna, String fecha, String fechaInicial, Foto[] fotos) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img = img;
        this.tipoEmbebido = tipoEmbebido;
        this.idEmbebido = idEmbebido;
        this.urlStream = urlStream;
        this.urlVideo = urlVideo;
        this.urlInterna = urlInterna;
        this.fecha = fecha;
        this.fechaInicial = fechaInicial;
        this.fotos = fotos;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTipoEmbebido() {
        return tipoEmbebido;
    }

    public void setTipoEmbebido(String tipoEmbebido) {
        this.tipoEmbebido = tipoEmbebido;
    }

    public String getIdEmbebido() {
        return idEmbebido;
    }

    public void setIdEmbebido(String idEmbebido) {
        this.idEmbebido = idEmbebido;
    }

    public String getUrlStream() {
        return urlStream;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getFecha() {
        return Utilidades.changeDate(fecha);
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Foto[] getFotos() {
        return fotos;
    }

    public void setFotos(Foto[] fotos) {
        this.fotos = fotos;
    }

    public String getUrlInterna() {
        return urlInterna;
    }

    public void setUrlInterna(String urlInterna) {
        this.urlInterna = urlInterna;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
}