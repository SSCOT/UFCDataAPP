package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;
import android.util.EventLog;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class EventoProvider {

    private Context context;
    private LuchadorProvider luchadorProvider;

    public EventoProvider(Context context) {
        this.context = context;
    }

    public interface EventoListener {
        void onResponse(Evento[][] eventos);

        void onErrorResponse(VolleyError error);
    }

    public void getAll(final EventoListener listener) {
        luchadorProvider = new LuchadorProvider(this.context);
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_EVENTS, Evento[].class, null, new Response.Listener<Evento[]>() {



            @Override
            public void onResponse(Evento[] events) {

                int numeroEventos = events.length;

                List<Evento> eventosPasadosAux = new ArrayList<Evento>();
                List<Evento> eventosProximosAux = new ArrayList<Evento>();
                Evento[] eventosPasados;
                Evento[] eventosProximos;


                List<Evento> eventosExtra = new ArrayList<Evento>();

                for (Evento currentEvent : events) {

                    if (currentEvent.getEstado().equals("POST_EVENT")) {
                        eventosPasadosAux.add(currentEvent);
                    } else if (currentEvent.getEstado().equals("FINALIZED")) {
                        eventosProximosAux.add(currentEvent);
                    }
                }

                int contador = eventosPasadosAux.size();

                Evento[][] eventsFinal = new Evento[2][];

                eventsFinal[0] = eventosPasadosAux.toArray(new Evento[0]);
                eventsFinal[1] = eventosProximosAux.toArray(new Evento[0]);

                listener.onResponse(eventsFinal);
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
