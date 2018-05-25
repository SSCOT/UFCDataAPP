package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Alarms.AlarmReceiver;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        // Premium
        luchadorProvider = new LuchadorProvider(this.context);

        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_EVENTS, Evento[].class, null, new Response.Listener<Evento[]>() {
            @Override
            public void onResponse(Evento[] events) {

                // fecha actual
                int fechaActualInt = Utilidades.getFechaActualInt();

                // Separamos eventos pasados de próximos
                splitEvents(events, fechaActualInt);

                // Comprobamos la fecha de actualización. Si es menor que la actual cambiamos el valor
                checkAndChangeDateSync(events, fechaActualInt);

                // TODO Borrar esto que es una prueba
                configureAlarm();

                listener.onResponse(eventsFinal);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Recuperamos datos en local para mostrar los últimos datos que teníamos
                EventoLocalProvider localProvider = new EventoLocalProvider(context);
                localProvider.getAll(new EventoLocalProvider.EventoLocalProviderListener() {
                    @Override
                    public void onResponse(Evento[] events) {
                        // fecha actual
                        int fechaActualInt = Utilidades.getFechaActualInt();
                        splitEvents(events, fechaActualInt);
                        listener.onResponse(eventsFinal);
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

    private void splitEvents(Evento[] events, int fechaActualInt) {
        List<Evento> eventosPasadosAux = new ArrayList<Evento>();
        List<Evento> eventosProximosAux = new ArrayList<Evento>();

        for (Evento currentEvent : events) {
            String fechaEvento = currentEvent.getFecha().replace("-", "");
            int fechaEventoInt = Integer.parseInt(fechaEvento);

            if (fechaEventoInt < fechaActualInt) {
                // eventosPasadosAux.add(eventosPasadosAux.size(),currentEvent);
                eventosPasadosAux.add(currentEvent);
            } else if (fechaEventoInt >= fechaActualInt) {
                eventosProximosAux.add(0, currentEvent);
            }
        }

        eventsFinal = new Evento[2][];

        eventsFinal[0] = eventosPasadosAux.toArray(new Evento[0]);
        eventsFinal[1] = eventosProximosAux.toArray(new Evento[0]);
    }

    private void checkAndChangeDateSync(Evento[] events, int fechaActualInt) {
        // obtenemos el dateSync de las shared preferences
        SharedPreferences preferences = context.getSharedPreferences("dbAuxiliar", Context.MODE_PRIVATE);
        String fechaActualizacion = preferences.getString("dateSync", "");

        // Si no había definida ninguna fecha o si es menor que la fecha actual, hay que volver a guardar todos los datos
        if (fechaActualizacion.equals("") || fechaActualInt > Integer.parseInt(fechaActualizacion)) {

            // Actualizamos Shared preferences
            changeDateSyncAndUpdateFlags(preferences);

            // Guardamos eventos en persistencia local
            saveEvents(events);

            // Al haber cambios, configuramos la alarma para el próximo evento
            // configureAlarm();
        }
    }

    private void changeDateSyncAndUpdateFlags(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        // Como vienen ordenados por fecha cogemos la fecha más próxima
        editor.putString("dateSync", eventsFinal[1][0].getFecha().replace("-", ""));
        // flags a false para que se vuelvan a actualizar los luchadores y campeones
        editor.putBoolean("fightersUpdated", false);
        editor.putBoolean("championsUpdated", false);
        editor.apply();
    }

    private void saveEvents(Evento[] events) {
        EventoLocalProvider localProvider = new EventoLocalProvider(context);
        localProvider.insert(events);
    }

    private void configureAlarm() {
        // TODO configurar alarma
        Calendar calendar = Calendar.getInstance();

                /*
                calendar.set(Calendar.MONTH, 2);
                calendar.set(Calendar.YEAR, 2017);
                calendar.set(Calendar.DAY_OF_MONTH, 28);

                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 48);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.AM_PM,Calendar.PM);
                */

        calendar.add(Calendar.SECOND, 5);

        Intent myIntent = new Intent(context, AlarmReceiver.class);
        Bundle bundle = new Bundle();
        Evento pruebaEvent = eventsFinal[1][0];
        bundle.putString("titulo", pruebaEvent.getTitulo());
        bundle.putString("subtitulo", pruebaEvent.getSubtitulo());
        bundle.putString("img1", pruebaEvent.getImgPrincipal());
        bundle.putString("img2", pruebaEvent.getImgSecundaria());
        myIntent.putExtras(bundle);
        // myIntent.putExtra("evento", pruebaEvent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getApplicationContext().getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        Log.d("-***", "onReceive: ENVIA AL ONRECEIVE");
    }
}
