package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public interface EventoFightsListener {
        void onResponse(Combate[] fights);

        void onErrorResponse(VolleyError error);
    }

    public void getAll(final EventoListener listener) {
        // free
        luchadorProvider = new LuchadorProvider(this.context);
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_EVENTS, Evento[].class, null, new Response.Listener<Evento[]>() {
            @Override
            public void onResponse(Evento[] events) {

                // fecha actual
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
                Date date = new Date();
                String fecha = dateFormat.format(date);
                int fechaActualInt = Integer.parseInt(fecha);

                int numeroEventos = events.length;

                List<Evento> eventosPasadosAux = new ArrayList<Evento>();
                List<Evento> eventosProximosAux = new ArrayList<Evento>();
                Evento[] eventosPasados;
                Evento[] eventosProximos;


                List<Evento> eventosExtra = new ArrayList<Evento>();

                for (Evento currentEvent : events) {
                    String fechaEvento = currentEvent.getFecha().replace("-","");
                    int fechaEventoInt = Integer.parseInt(fechaEvento);


                    if(fechaEventoInt < fechaActualInt) {
                        // eventosPasadosAux.add(eventosPasadosAux.size(),currentEvent);
                        eventosPasadosAux.add(currentEvent);
                    } else if (fechaEventoInt >= fechaActualInt) {
                        eventosProximosAux.add(0,currentEvent);
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

    public void getFights(int idEvento, final EventoFightsListener listener) {
        GsonRequest gsonRequest = new GsonRequest<>(String.format(BuildConfig.API_URL_GET_FIGHTS, idEvento), Combate[].class, null, new Response.Listener<Combate[]>() {
            @Override
            public void onResponse(Combate[] fights) {
                listener.onResponse(fights);
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
