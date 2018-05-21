package com.sergio.ufcdataappinicial.ufcdataappPremium.Data.Providers;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventoProvider {

    private Context context;
    private LuchadorProvider luchadorProvider;
    Evento[][] eventsFinal;

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
        luchadorProvider = new LuchadorProvider(this.context);
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_EVENTS, Evento[].class, null, new Response.Listener<Evento[]>() {
            @Override
            public void onResponse(Evento[] events) {

                // fecha actual
                int fechaActualInt = Utilidades.getFechaActualInt();

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

                eventsFinal = new Evento[2][];

                eventsFinal[0] = eventosPasadosAux.toArray(new Evento[0]);
                eventsFinal[1] = eventosProximosAux.toArray(new Evento[0]);

                // COMPROBAMOS LA FECHA DE ACTUALIZACIÓN. SI ES MENOR A LA ACTUAL CAMBIAMOS EL VALOR
                // obtenemos el dateSync de las shared preferences
                SharedPreferences preferences = context.getSharedPreferences("dbAuxiliar", Context.MODE_PRIVATE);
                String fechaActualizacion = preferences.getString("dateSync", "");

                // Si no había definida ninguna fecha o si es menor que la fecha actual, hay que volver a guardar todos los datos
                if (fechaActualizacion.equals("") || fechaActualInt > Integer.parseInt(fechaActualizacion)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("dateSync", eventsFinal[1][0].getFecha().replace("-", ""));
                    editor.putBoolean("fightersUpdated",false);
                    editor.putBoolean("championsUpdated",false);
                    editor.apply();
                }

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
