package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LuchadorCombate  implements Serializable {
    @SerializedName("Event")
    private CombateEvento evento;
    @SerializedName("Opponent")
    private CombateOponente oponente;
    @SerializedName("Result")
    private CombateResultado resultado;

    public LuchadorCombate(CombateEvento evento, CombateOponente oponente, CombateResultado resultado) {
        this.evento = evento;
        this.oponente = oponente;
        this.resultado = resultado;
    }

    public CombateEvento getEvento() {
        return evento;
    }

    public void setEvento(CombateEvento evento) {
        this.evento = evento;
    }

    public CombateOponente getOponente() {
        return oponente;
    }

    public void setOponente(CombateOponente oponente) {
        this.oponente = oponente;
    }

    public CombateResultado getResultado() {
        return resultado;
    }

    public void setResultado(CombateResultado resultado) {
        this.resultado = resultado;
    }
}
