package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.bbdd.UfcDatabase;

import java.util.List;

public class NoticiaLocalProvider {
    static UfcDatabase db;
    static Context context;

    public NoticiaLocalProvider(Context context) {
        this.context = context;
    }

    public interface NoticiaLocalProviderListener {
        void onResponse(Noticia[] noticias);

        void onErrorResponse(VolleyError error);
    }

    public interface NoticiaLocalUniqueProviderListener {
        void onResponse(Noticia noticia);

        void onErrorResponse(VolleyError error);
    }


    // INSERT
    public static void insert(Noticia[] noticias) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        for (Noticia item : noticias) {
            NoticiaLocalProvider.InsertTask insertTask = new NoticiaLocalProvider.InsertTask();
            insertTask.execute(item);
        }
    }

    public static void insertOne(Noticia luchador) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        NoticiaLocalProvider.InsertTask insertTask = new NoticiaLocalProvider.InsertTask();
        insertTask.execute(luchador);
    }

    // GET
    public static void getAll(final NoticiaLocalProvider.NoticiaLocalProviderListener listener) {
        // listenerGlobal = listener;
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        NoticiaLocalProvider.GetAllAsyncTask getAllAsyncTask = new NoticiaLocalProvider.GetAllAsyncTask(listener);
        getAllAsyncTask.execute();
    }

    // Delete
    public static void deleteAll() {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        DeleteAllAsyncTask deleteAllAsyncTask = new DeleteAllAsyncTask();
        deleteAllAsyncTask.execute();
    }

    public static void getNew(String idNoticia, final NoticiaLocalProvider.NoticiaLocalUniqueProviderListener listener) {
        db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
        NoticiaLocalProvider.GetOneAsyncTask getOneAsyncTask = new NoticiaLocalProvider.GetOneAsyncTask(idNoticia, listener);
        getOneAsyncTask.execute();
    }

    // ASYNC TASKS

    private static class GetAllAsyncTask extends AsyncTask<Void, Integer, List<Noticia>> {
        NoticiaLocalProvider.NoticiaLocalProviderListener listener;
        public GetAllAsyncTask(NoticiaLocalProvider.NoticiaLocalProviderListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Noticia> doInBackground(Void... voids) {
            return db.ufcDao().getAllNews();
        }

        @Override
        protected void onPostExecute(List<Noticia> noticias) {
            super.onPostExecute(noticias);
            Noticia[] noticiasFinal = noticias.toArray(new Noticia[noticias.size()]);
            listener.onResponse(noticiasFinal);
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            db = Room.databaseBuilder(context, UfcDatabase.class, "ufcDb").build();
            db.ufcDao().deleteNews();
            return null;
        }
    }

    private static class GetOneAsyncTask extends AsyncTask<Void, Integer, Noticia> {
        String idNoticia;
        NoticiaLocalProvider.NoticiaLocalUniqueProviderListener listener;
        public GetOneAsyncTask(String idNoticia, NoticiaLocalProvider.NoticiaLocalUniqueProviderListener listener) {
            this.idNoticia = idNoticia;
            this.listener = listener;
        }

        @Override
        protected Noticia doInBackground(Void... voids) {
            return db.ufcDao().getNew(Integer.parseInt(idNoticia));
        }

        @Override
        protected void onPostExecute(Noticia noticia) {
            super.onPostExecute(noticia);
            listener.onResponse(noticia);
        }
    }

    private static class InsertTask extends AsyncTask<Noticia, Void, Void> {
        @Override
        protected Void doInBackground(Noticia... noticias) {
            if (db != null)
                db.ufcDao().insertNew(noticias);
            return null;
        }
    }
}
