package com.sergio.ufcdataappinicial.ufcdataapp.Data.bbdd;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Prueba;

import java.util.List;

@Dao
public interface UfcDao {

    @Query("SELECT * FROM luchador")
    List<Luchador> getAllFighters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Luchador luchador);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLuchador(Luchador...luchadores);

    @Delete()
    void delete(Luchador luchador);
}
