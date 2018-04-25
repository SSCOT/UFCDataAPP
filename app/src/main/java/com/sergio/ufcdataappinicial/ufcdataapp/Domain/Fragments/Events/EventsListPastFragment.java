package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.EventoProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.EventsAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class EventsListPastFragment extends Fragment {

    @BindView(R.id.rvEvents)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarEvents)
    ProgressBar progressBar;

    String tipo;

    EventoProvider eventProvider;
    LuchadorProvider luchadorProvider;

    public EventsListPastFragment() {

    }

    public static EventsListPastFragment newInstance() {
        EventsListPastFragment fragment = new EventsListPastFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventProvider = new EventoProvider(getContext());
        luchadorProvider = new LuchadorProvider(getContext());
        recyclerConf(getView());
        setLoading(true);
        getEvents();
    }

    private void recyclerConf(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void getEvents() {
        eventProvider.getAll(new EventoProvider.EventoListener() {
            @Override
            public void onResponse(final Evento[][] eventos) {

                /*RequestFuture<JSONObject> future = RequestFuture.newFuture();
                JsonObjectRequest request = new JsonObjectRequest("http://ufc-data-api.ufc.com/api/v3/es/fighters/241895.json", new JSONObject(), future, future);
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
                requestQueue.add(request);
                JSONObject response = null;
                try {
                    Log.d("", "onResponse: ");
                    response = future.get(60, TimeUnit.SECONDS); // this will block
                    Log.d("", "onResponse: ");
                } catch (InterruptedException e) {
                    // exception handling
                } catch (ExecutionException e) {
                    // exception handling
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }*/

                /*// Sacamos los luchadores del main card de cada evento
                for (int i = 0; i < eventos[0].length; i++) {

                    final Evento currentEvent = eventos[0][i];
                    final int indice = i;

                    luchadorProvider.getFighter(String.valueOf(currentEvent.getIdLuchador1()), new LuchadorProvider.LuchadorUniqueProviderListener() {
                        @Override
                        public void onResponse(Luchador luchador1) {
                            eventos[0][indice].setLuchador1(luchador1);
                            luchadorProvider.getFighter(String.valueOf(currentEvent.getIdLuchador2()), new LuchadorProvider.LuchadorUniqueProviderListener() {
                                @Override
                                public void onResponse(Luchador luchador2) {
                                    eventos[0][indice].setLuchador1(luchador2);
                                    setLoading(false);
                                    EventsAdapter adapter = new EventsAdapter(getActivity(), eventos[0]);
                                    recyclerView.setAdapter(adapter);
                                }

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO sustituir toast
                                    Toast.makeText(getContext(), "Error1", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO sustituir toast
                            Toast.makeText(getContext(), "Error2 - "+currentEvent.getIdLuchador1(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }*/

                setLoading(false);
                EventsAdapter adapter = new EventsAdapter(getActivity(), eventos[0]);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                // TODO Sustituir TOAST
                Toast.makeText(getActivity(), "Error al recoger los datos", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
