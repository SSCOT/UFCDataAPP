package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;

public class LuchadorProvider {
    private RequestQueue mRequestQueue;
    static Context context;

    public LuchadorProvider(Context context) {
        this.context = context;
    }

    public interface LuchadorProviderListener {
        void onResponse(Luchador[] luchadores);

        void onErrorResponse(VolleyError error);
    }

    public interface LuchadorUniqueProviderListener {
        void onResponse(Luchador luchador);

        void onErrorResponse(VolleyError error);
    }

    public void getAll(final LuchadorProvider.LuchadorProviderListener listener) {
        // free
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_FIGHTERS_ALL, Luchador[].class, null, new Response.Listener<Luchador[]>() {

            @Override
            public void onResponse(Luchador[] luchadores) {
                listener.onResponse(luchadores);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });

        RequestManager.getInstance().addToRequestQueue(context, gsonRequest);
    }

    public void getChampions(final LuchadorProvider.LuchadorProviderListener listener) {
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_FIGHTERS_CHAMPIONS, Luchador[].class, null, new Response.Listener<Luchador[]>() {

            @Override
            public void onResponse(Luchador[] luchadores) {
                listener.onResponse(luchadores);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });

        RequestManager.getInstance().addToRequestQueue(context, gsonRequest);
    }
    public void getFighter(String idLuchador, final LuchadorProvider.LuchadorUniqueProviderListener listener) {
        String url = String.format(BuildConfig.API_URL_GET_FIGHTER, idLuchador);
        GsonRequest gsonRequest = new GsonRequest<>(url, Luchador.class, null, new Response.Listener<Luchador>() {

            @Override
            public void onResponse(Luchador luchador) {
                listener.onResponse(luchador);
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
