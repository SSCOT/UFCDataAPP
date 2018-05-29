package com.sergio.ufcdataappinicial.ufcdataapp.Data.bbdd;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;

import java.util.List;

@Dao
public interface UfcDao {

    // Luchadores
    @Query("SELECT * FROM luchador")
    List<Luchador> getAllFighters();

    @Query("SELECT * FROM luchador WHERE id = :id")
    Luchador getFighter(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLuchadores(Luchador luchador);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLuchador(Luchador... luchadores);

    @Delete()
    void delete(Luchador luchador);

    // Noticias
    @Query("SELECT * FROM noticia")
    List<Noticia> getAllNews();

    @Query("SELECT * FROM noticia WHERE id = :id")
    Noticia getNew(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(Noticia noticia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNew(Noticia... noticias);

    @Delete()
    void deleteNew(Noticia noticia);

    // Eventos
    @Query("SELECT * FROM evento")
    List<Evento> getAllEvents();

    @Query("SELECT * FROM evento WHERE id = :id")
    Evento getEvent(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvents(Evento evento);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(Evento... eventos);

    @Delete()
    void deleteEvent(Evento evento);

    @Query("DELETE FROM evento")
    void deleteEvents();
    @Query("DELETE FROM noticia")
    void deleteNews();
    @Query("DELETE FROM luchador")
    void deleteFighters();
}
