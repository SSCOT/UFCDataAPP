package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;

public class NoticiaProvider {
    Context context;

    public NoticiaProvider(Context context) {
        this.context = context;
    }

    public interface NoticiasProviderListener {
        public void onResponse(Noticia[] news);
        public void onErrorResponse(VolleyError error);
    }

    public void getArticles(final NoticiasProviderListener listener) {
        // Obtenemos la localización para saber el contenido a mostrar
        SharedPreferences preferences = context.getSharedPreferences("dbAuxiliar", Context.MODE_PRIVATE);
        // int idLocalization = preferences.getInt("idLocalization", 5);
        int idLocalization = preferences.getInt("idLocalization", 1);
        String url = String.format(BuildConfig.API_URL_GET_NEWS,idLocalization);

        GsonRequest gsonRequest = new GsonRequest<>(url, Noticia[].class, null, new Response.Listener<Noticia[]>() {

            @Override
            public void onResponse(Noticia[] news) {
                // Las noticias es algo muy cambiante y de poco peso. Guardamos siempre en local
                saveData(news);
                listener.onResponse(news);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Recuperamos datos en local para mostrar los últimos datos que teníamos
                NoticiaLocalProvider noticiaLocalProvider = new NoticiaLocalProvider(context);
                noticiaLocalProvider.getAll(new NoticiaLocalProvider.NoticiaLocalProviderListener() {
                    @Override
                    public void onResponse(Noticia[] news) {
                        listener.onResponse(news);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onErrorResponse(error);
                    }
                });
            }
        });

        RequestManager.getInstance().addToRequestQueue(context, gsonRequest);
    }

    private void saveData(Noticia[] news) {
        NoticiaLocalProvider localProvider = new NoticiaLocalProvider(context);
        localProvider.insert(news);
    }
}
