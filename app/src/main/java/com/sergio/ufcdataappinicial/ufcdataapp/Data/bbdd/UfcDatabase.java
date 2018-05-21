package com.sergio.ufcdataappinicial.ufcdataapp.Data.bbdd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Prueba;

@Database(entities = {Luchador.class, Noticia.class}, version = 1, exportSchema = false)
public abstract class UfcDatabase extends RoomDatabase {
    public abstract UfcDao ufcDao();
}