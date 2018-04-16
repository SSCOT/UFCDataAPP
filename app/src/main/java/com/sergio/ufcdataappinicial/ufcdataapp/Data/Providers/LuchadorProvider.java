package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;

public class LuchadorProvider {

    Context context;

    public LuchadorProvider(Context context) {
        this.context = context;
    }

    public interface LuchadorProviderListener {
        public void onResponse(Luchador[] luchadores);
        public void onErrorResponse(VolleyError error);
    }

    public void getAll(final LuchadorProviderListener listener) {
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

    public void getChampions(final LuchadorProviderListener listener){
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

    public void getFightersWeightClass(String weightClass, final LuchadorProviderListener listener) {

        GsonRequest gsonRequest = new GsonRequest<>(String.format(BuildConfig.API_URL_GET_FIGHTERS_WEIGHT_CLASS, weightClass), Luchador[].class, null, new Response.Listener<Luchador[]>() {

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

    public void getFighter(String idLuchador, final LuchadorProviderListener listener) {
        GsonRequest gsonRequest = new GsonRequest<>(String.format(BuildConfig.API_URL_GET_FIGHTERS_WEIGHT_CLASS, idLuchador), Luchador[].class, null, new Response.Listener<Luchador[]>() {

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
}
