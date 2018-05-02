package com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CombateResultado  implements Serializable {
    @SerializedName("Outcome")
    private String resultado;
    @SerializedName("Method")
    private String tipoFinalizacion;

    public CombateResultado(String resultado, String tipoFinalizacion) {
        this.resultado = resultado;
        this.tipoFinalizacion = tipoFinalizacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTipoFinalizacion() {
        return tipoFinalizacion;
    }

    public void setTipoFinalizacion(String tipoFinalizacion) {
        this.tipoFinalizacion = tipoFinalizacion;
    }
}

