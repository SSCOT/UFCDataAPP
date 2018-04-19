package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
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
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_NEWS, Noticia[].class, null, new Response.Listener<Noticia[]>() {

            @Override
            public void onResponse(Noticia[] news) {
                listener.onResponse(news);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });

        RequestManager.getInstance().addToRequestQueue(context, gsonRequest);
    }
}
