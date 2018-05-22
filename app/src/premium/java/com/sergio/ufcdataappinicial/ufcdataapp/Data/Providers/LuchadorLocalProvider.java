package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.bbdd.UfcDatabase;

import java.util.List;

public class LuchadorLocalProvider {
    static UfcDatabase db;

    // private RequestQueue mRequestQueue;
    static Context context;

    public LuchadorLocalProvider(Context context) {
        this.context = context;
    }

    public interface LuchadorLocalProviderListener {
        void onResponse(Luchador[] luchadores);

        void onErrorResponse(VolleyError error);
    }

    public interface LuchadorLocalUniqueProviderListener {
        void onResponse(Luchador luchador);

        void onErrorResponse(VolleyError error);
    }


    // INSERT
    public static void insert(Luchador[] luchadores) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        for (Luchador itemLuchador : luchadores) {
            InsertTask insertTask = new InsertTask();
            insertTask.execute(itemLuchador);
        }
    }

    public static void insertOne(Luchador luchador) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        InsertTask insertTask = new InsertTask();
        insertTask.execute(luchador);
    }

    // GET
    public static void getAll(final LuchadorLocalProviderListener listener) {
        // listenerGlobal = listener;
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        GetAllAsyncTask getAllAsyncTask = new GetAllAsyncTask(listener);
        getAllAsyncTask.execute();
    }

    public static void getFighter(String idLuchador, final LuchadorLocalUniqueProviderListener listener) {
        // listenerUniqueGlobal = listener;
        // idLuchadorGlobal = idLuchador;
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        GetOneAsyncTask getOneAsyncTask = new GetOneAsyncTask(idLuchador, listener);
        getOneAsyncTask.execute();
    }

    // ASYNC TASKS

    private static class GetAllAsyncTask extends AsyncTask<Void, Integer, List<Luchador>> {
        LuchadorLocalProviderListener listener;
        public GetAllAsyncTask(LuchadorLocalProviderListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Luchador> doInBackground(Void... voids) {
            return db.ufcDao().getAllFighters();
        }

        @Override
        protected void onPostExecute(List<Luchador> luchadores) {
            super.onPostExecute(luchadores);
            Luchador[] luchadoresFinal = luchadores.toArray(new Luchador[luchadores.size()]);
            listener.onResponse(luchadoresFinal);
        }
    }

    private static class GetOneAsyncTask extends AsyncTask<Void, Integer, Luchador> {
        String idLuchador;
        LuchadorLocalUniqueProviderListener listener;
        public GetOneAsyncTask(String idLuchador, LuchadorLocalUniqueProviderListener listener) {
            this.idLuchador = idLuchador;
            this.listener = listener;
        }

        @Override
        protected Luchador doInBackground(Void... voids) {
            return db.ufcDao().getFighter(Integer.parseInt(idLuchador));
        }

        @Override
        protected void onPostExecute(Luchador luchador) {
            super.onPostExecute(luchador);
            listener.onResponse(luchador);
        }
    }

    private static class InsertTask extends AsyncTask<Luchador, Void, Void> {
        @Override
        protected Void doInBackground(Luchador... luchadores) {
            if (db != null)
                db.ufcDao().insertLuchador(luchadores);
            return null;
        }
    }


}
