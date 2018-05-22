package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.bbdd.UfcDatabase;

import java.util.List;

public class EventoLocalProvider {

    static UfcDatabase db;
    static Context context;

    public EventoLocalProvider(Context context) {
        this.context = context;
    }

    public interface EventoLocalProviderListener {
        void onResponse(Evento[] eventos);

        void onErrorResponse(VolleyError error);
    }

    public interface EventoLocalUniqueProviderListener {
        void onResponse(Evento evento);

        void onErrorResponse(VolleyError error);
    }


    // INSERT
    public static void insert(Evento[] eventos) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        for (Evento item : eventos) {
            InsertTask insertTask = new InsertTask();
            insertTask.execute(item);
        }
    }

    public static void insertOne(Evento evento) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        InsertTask insertTask = new InsertTask();
        insertTask.execute(evento);
    }

    // GET
    public static void getAll(final EventoLocalProviderListener listener) {
        // listenerGlobal = listener;
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        GetAllAsyncTask getAllAsyncTask = new GetAllAsyncTask(listener);
        getAllAsyncTask.execute();
    }

    public static void getNew(String idEvento, final EventoLocalUniqueProviderListener listener) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        GetOneAsyncTask getOneAsyncTask = new GetOneAsyncTask(idEvento, listener);
        getOneAsyncTask.execute();
    }

    // ASYNC TASKS

    private static class GetAllAsyncTask extends AsyncTask<Void, Integer, List<Evento>> {
        EventoLocalProviderListener listener;
        public GetAllAsyncTask(EventoLocalProviderListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Evento> doInBackground(Void... voids) {
            return db.ufcDao().getAllEvents();
        }

        @Override
        protected void onPostExecute(List<Evento> eventos) {
            super.onPostExecute(eventos);
            Evento[] eventosFinal = eventos.toArray(new Evento[eventos.size()]);
            listener.onResponse(eventosFinal);
        }
    }

    private static class GetOneAsyncTask extends AsyncTask<Void, Integer, Evento> {
        String idEvento;
        EventoLocalUniqueProviderListener listener;
        public GetOneAsyncTask(String idEvento, EventoLocalUniqueProviderListener listener) {
            this.idEvento = idEvento;
            this.listener = listener;
        }

        @Override
        protected Evento doInBackground(Void... voids) {
            return db.ufcDao().getEvent(Integer.parseInt(idEvento));
        }

        @Override
        protected void onPostExecute(Evento evento) {
            super.onPostExecute(evento);
            listener.onResponse(evento);
        }
    }

    private static class InsertTask extends AsyncTask<Evento, Void, Void> {
        @Override
        protected Void doInBackground(Evento... eventos) {
            if (db != null)
                db.ufcDao().insertEvent(eventos);
            return null;
        }
    }

}
