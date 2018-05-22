package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

public class LuchadorProvider {

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

    public void getAll(final LuchadorProviderListener listener) {
        // premium
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_FIGHTERS_ALL, Luchador[].class, null, new Response.Listener<Luchador[]>() {
            @Override
            public void onResponse(Luchador[] luchadores) {
                // Guardado local si procede
                localCheckAndSave(luchadores);
                listener.onResponse(luchadores);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Recuperamos datos en local para mostrar los últimos datos que teníamos
                LuchadorLocalProvider luchadorLocalProvider = new LuchadorLocalProvider(context);
                luchadorLocalProvider.getAll(new LuchadorLocalProvider.LuchadorLocalProviderListener() {
                    @Override
                    public void onResponse(Luchador[] luchadores) {
                        listener.onResponse(luchadores);
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

    public void getChampions(final LuchadorProviderListener listener) {
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_FIGHTERS_CHAMPIONS, Luchador[].class, null, new Response.Listener<Luchador[]>() {

            @Override
            public void onResponse(Luchador[] luchadores) {
                listener.onResponse(luchadores);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
               /* LuchadorLocalProvider luchadorLocalProvider = new LuchadorLocalProvider(context);
                luchadorLocalProvider.getAllChampions(new LuchadorLocalProvider.LuchadorLocalProviderListener() {
                    @Override
                    public void onResponse(Luchador[] luchadores) {
                        listener.onResponse(luchadores);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onErrorResponse(error);
                    }
                });*/
                listener.onErrorResponse(error);
            }
        });

        RequestManager.getInstance().addToRequestQueue(context, gsonRequest);
    }

    public void getFighter(final String idLuchador, final LuchadorUniqueProviderListener listener) {
        String url = String.format(BuildConfig.API_URL_GET_FIGHTER, idLuchador);
        GsonRequest gsonRequest = new GsonRequest<>(url, Luchador.class, null, new Response.Listener<Luchador>() {

            @Override
            public void onResponse(Luchador luchador) {
                listener.onResponse(luchador);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                LuchadorLocalProvider luchadorLocalProvider = new LuchadorLocalProvider(context);
                luchadorLocalProvider.getFighter(idLuchador, new LuchadorLocalProvider.LuchadorLocalUniqueProviderListener() {
                    @Override
                    public void onResponse(Luchador luchador) {
                        listener.onResponse(luchador);
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

    private void localCheckAndSave(Luchador[] luchadores) {

        /*
         * Comprobamos en las shared preferences si la fecha actual ha superado la fecha de sincronización
         * DE ser así tenemos que volver a guardar en local
         * */

        // Sacamos fecha de actualización y el bool de luchadores actualizados de shared preferences
        SharedPreferences preferences = context.getSharedPreferences("dbAuxiliar", Context.MODE_PRIVATE);
        int fechaSync = Integer.parseInt(preferences.getString("dateSync", "0"));
        boolean dataUpdated =  preferences.getBoolean("fightersUpdated", false);

        // Obtenemos la fecha actual
        int fechaActualInt = Utilidades.getFechaActualInt();

        // Comprobamos si la fecha actual es mayor y si el boolean de datos actualizados es false
        if(fechaActualInt > fechaSync && !dataUpdated) {
            // Al haber pasado la fecha del último evento, volvemos a guardar los datos de luchadores en local
            LuchadorLocalProvider localProvider = new LuchadorLocalProvider(context);
            localProvider.insert(luchadores);

            /*
             * Indicamos a las shared preferences que los datos han sido actualizados.
             * Esto lo hacemos porque si no entramos en "eventos" no cambiaremos la fecha de actualización
             * y de esta forma aunque eso ocurra ya no volveremos a guardar los datos innecesariamente
             * */
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("fightersUpdated",true);
            editor.apply();
        }
    }
}
