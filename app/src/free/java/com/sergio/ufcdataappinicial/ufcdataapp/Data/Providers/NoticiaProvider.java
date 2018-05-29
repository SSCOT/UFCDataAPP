package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;

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
        // Versión US original
        String url = String.format(BuildConfig.API_URL_GET_NEWS,1);
        GsonRequest gsonRequest = new GsonRequest<>(url, Noticia[].class, null, new Response.Listener<Noticia[]>() {

            @Override
            public void onResponse(Noticia[] news) {
                // Las noticias son algo cambiante. SIEMPRE las guardamos

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
