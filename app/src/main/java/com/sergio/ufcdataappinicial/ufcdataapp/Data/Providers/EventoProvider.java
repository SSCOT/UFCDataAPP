package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;

public class EventoProvider {

    private Context context;

    public EventoProvider(Context context) {
        this.context = context;
    }

    private interface EventoListener {
        public void onResponse(Evento[] eventos);
        public void onErrorResponse(VolleyError error);
    }

    public void getAll(final EventoListener listener) {
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_FIGHTERS_ALL, Evento[].class, null, new Response.Listener<Evento[]>() {

            @Override
            public void onResponse(Evento[] luchadores) {
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
