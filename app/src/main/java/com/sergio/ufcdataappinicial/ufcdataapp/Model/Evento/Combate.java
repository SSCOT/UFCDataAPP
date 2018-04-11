package com.sergio.ufcdataappinicial.ufcdataapp.Model.Evento;

import com.google.gson.annotations.SerializedName;

public class Combate {

    private int id;

    @SerializedName("fighter1_first_name")
    private String nombre1;
    @SerializedName("fighter1_last_name")
    private String apellido1;
    @SerializedName("fighter1_nickname")
    private String nick1;
    @SerializedName("fighter1height")
    private int altura1;
    @SerializedName("fighter1weight")
    private int peso1;
    @SerializedName("fighter1record")
    private String record1;
    @SerializedName("fighter1_rank")
    private String pos1;
    @SerializedName("fighter1_full_body_image")
    private String imgCuerpo1;
    @SerializedName("fighter1_profile_image")
    private String imgPerfil1;
    @SerializedName("fighter1_kdaverage")
    private String knockDownAvg1;
    @SerializedName("fighter1_strikingaccuracy")
    private String strikingAcc1;
    @SerializedName("fighter1_strikingdefense")
    private String strikingDef1;
    @SerializedName("fighter1_takedownaverage")
    private String takeDownAvg1;
    @SerializedName("fighter1_takedownaccuracy")
    private String takeDownAcc1;
    @SerializedName("fighter1_takedowndefense")
    private String takeDownDef1;
    @SerializedName("fighter1_submissionsaverage")
    private String sumisionAvg1;
    @SerializedName("fighter1_is_winner")
    private Boolean ganador1;

    @SerializedName("fighter2_first_name")
    private String nombre2;
    @SerializedName("fighter2_last_name")
    private String apellido2;
    @SerializedName("fighter2_nickname")
    private String nick2;
    @SerializedName("fighter2height")
    private int altura2;
    @SerializedName("fighter2weight")
    private int peso2;
    @SerializedName("fighter2record")
    private String record2;
    @SerializedName("fighter2_rank")
    private String pos2;
    @SerializedName("fighter2_full_body_image")
    private String imgCuerpo2;
    @SerializedName("fighter2_profile_image")
    private String imgPerfil2;
    @SerializedName("fighter2_kdaverage")
    private String knockDownAvg2;
    @SerializedName("fighter2_strikingaccuracy")
    private String strikingAcc2;
    @SerializedName("fighter2_strikingdefense")
    private String strikingDef2;
    @SerializedName("fighter2_takedownaverage")
    private String takeDownAvg2;
    @SerializedName("fighter2_takedownaccuracy")
    private String takeDownAcc2;
    @SerializedName("fighter2_takedowndefense")
    private String takeDownDef2;
    @SerializedName("fighter2_submissionsaverage")
    private String sumisionAvg2;
    @SerializedName("fighter2_is_winner")
    private Boolean ganador2;

    @SerializedName("result")
    private Resultado result;

    @SerializedName("fight_description")
    private String descripcion;

    @SerializedName("is_title_fight")
    private Boolean esPeleaDeTitulo;
    @SerializedName("is_main_event")
    private Boolean esPelaPrincipal;

    public Combate(int id, String nombre1, String apellido1, String nick1, int altura1, int peso1, String record1, String pos1, String imgCuerpo1, String imgPerfil1, String knockDownAvg1, String strikingAcc1, String strikingDef1, String takeDownAvg1, String takeDownAcc1, String takeDownDef1, String sumisionAvg1, Boolean ganador1, String nombre2, String apellido2, String nick2, int altura2, int peso2, String record2, String pos2, String imgCuerpo2, String imgPerfil2, String knockDownAvg2, String strikingAcc2, String strikingDef2, String takeDownAvg2, String takeDownAcc2, String takeDownDef2, String sumisionAvg2, Boolean ganador2, Resultado result, String descripcion, Boolean esPeleaDeTitulo, Boolean esPelaPrincipal) {
        super();

        this.id = id;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.nick1 = nick1;
        this.altura1 = altura1;
        this.peso1 = peso1;
        this.record1 = record1;
        this.pos1 = pos1;
        this.imgCuerpo1 = imgCuerpo1;
        this.imgPerfil1 = imgPerfil1;
        this.knockDownAvg1 = knockDownAvg1;
        this.strikingAcc1 = strikingAcc1;
        this.strikingDef1 = strikingDef1;
        this.takeDownAvg1 = takeDownAvg1;
        this.takeDownAcc1 = takeDownAcc1;
        this.takeDownDef1 = takeDownDef1;
        this.sumisionAvg1 = sumisionAvg1;
        this.ganador1 = ganador1;
        this.nombre2 = nombre2;
        this.apellido2 = apellido2;
        this.nick2 = nick2;
        this.altura2 = altura2;
        this.peso2 = peso2;
        this.record2 = record2;
        this.pos2 = pos2;
        this.imgCuerpo2 = imgCuerpo2;
        this.imgPerfil2 = imgPerfil2;
        this.knockDownAvg2 = knockDownAvg2;
        this.strikingAcc2 = strikingAcc2;
        this.strikingDef2 = strikingDef2;
        this.takeDownAvg2 = takeDownAvg2;
        this.takeDownAcc2 = takeDownAcc2;
        this.takeDownDef2 = takeDownDef2;
        this.sumisionAvg2 = sumisionAvg2;
        this.ganador2 = ganador2;
        this.result = result;
        this.descripcion = descripcion;
        this.esPeleaDeTitulo = esPeleaDeTitulo;
        this.esPelaPrincipal = esPelaPrincipal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getNick1() {
        return nick1;
    }

    public void setNick1(String nick1) {
        this.nick1 = nick1;
    }

    public int getAltura1() {
        return altura1;
    }

    public void setAltura1(int altura1) {
        this.altura1 = altura1;
    }

    public int getPeso1() {
        return peso1;
    }

    public void setPeso1(int peso1) {
        this.peso1 = peso1;
    }

    public String getRecord1() {
        return record1;
    }

    public void setRecord1(String record1) {
        this.record1 = record1;
    }

    public String getPos1() {
        return pos1;
    }

    public void setPos1(String pos1) {
        this.pos1 = pos1;
    }

    public String getImgCuerpo1() {
        return imgCuerpo1;
    }

    public void setImgCuerpo1(String imgCuerpo1) {
        this.imgCuerpo1 = imgCuerpo1;
    }

    public String getImgPerfil1() {
        return imgPerfil1;
    }

    public void setImgPerfil1(String imgPerfil1) {
        this.imgPerfil1 = imgPerfil1;
    }

    public String getKnockDownAvg1() {
        return knockDownAvg1;
    }

    public void setKnockDownAvg1(String knockDownAvg1) {
        this.knockDownAvg1 = knockDownAvg1;
    }

    public String getStrikingAcc1() {
        return strikingAcc1;
    }

    public void setStrikingAcc1(String strikingAcc1) {
        this.strikingAcc1 = strikingAcc1;
    }

    public String getStrikingDef1() {
        return strikingDef1;
    }

    public void setStrikingDef1(String strikingDef1) {
        this.strikingDef1 = strikingDef1;
    }

    public String getTakeDownAvg1() {
        return takeDownAvg1;
    }

    public void setTakeDownAvg1(String takeDownAvg1) {
        this.takeDownAvg1 = takeDownAvg1;
    }

    public String getTakeDownAcc1() {
        return takeDownAcc1;
    }

    public void setTakeDownAcc1(String takeDownAcc1) {
        this.takeDownAcc1 = takeDownAcc1;
    }

    public String getTakeDownDef1() {
        return takeDownDef1;
    }

    public void setTakeDownDef1(String takeDownDef1) {
        this.takeDownDef1 = takeDownDef1;
    }

    public String getSumisionAvg1() {
        return sumisionAvg1;
    }

    public void setSumisionAvg1(String sumisionAvg1) {
        this.sumisionAvg1 = sumisionAvg1;
    }

    public Boolean getGanador1() {
        return ganador1;
    }

    public void setGanador1(Boolean ganador1) {
        this.ganador1 = ganador1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNick2() {
        return nick2;
    }

    public void setNick2(String nick2) {
        this.nick2 = nick2;
    }

    public int getAltura2() {
        return altura2;
    }

    public void setAltura2(int altura2) {
        this.altura2 = altura2;
    }

    public int getPeso2() {
        return peso2;
    }

    public void setPeso2(int peso2) {
        this.peso2 = peso2;
    }

    public String getRecord2() {
        return record2;
    }

    public void setRecord2(String record2) {
        this.record2 = record2;
    }

    public String getPos2() {
        return pos2;
    }

    public void setPos2(String pos2) {
        this.pos2 = pos2;
    }

    public String getImgCuerpo2() {
        return imgCuerpo2;
    }

    public void setImgCuerpo2(String imgCuerpo2) {
        this.imgCuerpo2 = imgCuerpo2;
    }

    public String getImgPerfil2() {
        return imgPerfil2;
    }

    public void setImgPerfil2(String imgPerfil2) {
        this.imgPerfil2 = imgPerfil2;
    }

    public String getKnockDownAvg2() {
        return knockDownAvg2;
    }

    public void setKnockDownAvg2(String knockDownAvg2) {
        this.knockDownAvg2 = knockDownAvg2;
    }

    public String getStrikingAcc2() {
        return strikingAcc2;
    }

    public void setStrikingAcc2(String strikingAcc2) {
        this.strikingAcc2 = strikingAcc2;
    }

    public String getStrikingDef2() {
        return strikingDef2;
    }

    public void setStrikingDef2(String strikingDef2) {
        this.strikingDef2 = strikingDef2;
    }

    public String getTakeDownAvg2() {
        return takeDownAvg2;
    }

    public void setTakeDownAvg2(String takeDownAvg2) {
        this.takeDownAvg2 = takeDownAvg2;
    }

    public String getTakeDownAcc2() {
        return takeDownAcc2;
    }

    public void setTakeDownAcc2(String takeDownAcc2) {
        this.takeDownAcc2 = takeDownAcc2;
    }

    public String getTakeDownDef2() {
        return takeDownDef2;
    }

    public void setTakeDownDef2(String takeDownDef2) {
        this.takeDownDef2 = takeDownDef2;
    }

    public String getSumisionAvg2() {
        return sumisionAvg2;
    }

    public void setSumisionAvg2(String sumisionAvg2) {
        this.sumisionAvg2 = sumisionAvg2;
    }

    public Boolean getGanador2() {
        return ganador2;
    }

    public void setGanador2(Boolean ganador2) {
        this.ganador2 = ganador2;
    }

    public Resultado getResult() {
        return result;
    }

    public void setResult(Resultado result) {
        this.result = result;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEsPeleaDeTitulo() {
        return esPeleaDeTitulo;
    }

    public void setEsPeleaDeTitulo(Boolean esPeleaDeTitulo) {
        this.esPeleaDeTitulo = esPeleaDeTitulo;
    }

    public Boolean getEsPelaPrincipal() {
        return esPelaPrincipal;
    }

    public void setEsPelaPrincipal(Boolean esPelaPrincipal) {
        this.esPelaPrincipal = esPelaPrincipal;
    }
}