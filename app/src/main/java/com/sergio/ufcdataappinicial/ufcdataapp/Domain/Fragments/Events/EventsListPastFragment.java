package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.EventoProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.EventActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.EventsAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsListPastFragment extends Fragment {

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvEvents)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarEvents)
    ProgressBar progressBar;
    @BindView(R.id.etBuscador)
    SearchView etBuscador;

    EventoProvider eventProvider;
    LuchadorProvider luchadorProvider;

    ArrayList<Evento> eventsGeneral;
    EventsAdapter adapter;

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
        View view = inflater.inflate(R.layout.fragment_events_list_past, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventProvider = new EventoProvider(getContext());
        luchadorProvider = new LuchadorProvider(getContext());
        recyclerConf(getView());
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);
        getEvents();

        etBuscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Evento> listaEventosFiltrados = filter(eventsGeneral, newText);
                Evento[] eventosFiltrados = (Evento[]) listaEventosFiltrados.toArray(new Evento[listaEventosFiltrados.size()]);
                if (adapter != null)
                    adapter.setFilter(eventosFiltrados);

                return true;
            }

            public void callSearch(String query) {

            }

        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getEvents();
            }
        });
    }

    private void recyclerConf(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void getEvents() {
        // Sacamos todos los datos de los luchadores
        luchadorProvider.getAll(new LuchadorProvider.LuchadorProviderListener() {
            @Override
            public void onResponse(Luchador[] response) {
                final SparseArray<Luchador> luchadoresSparse = new SparseArray<Luchador>();
                for (Luchador item : response) {
                    luchadoresSparse.append(item.getId(), item);
                }
                eventProvider.getAll(new EventoProvider.EventoListener() {
                    @Override
                    public void onResponse(final Evento[][] eventos) {

                        for (Evento itemEvento : eventos[0]) {
                            itemEvento.setLuchador1(luchadoresSparse.get(itemEvento.getIdLuchador1()));
                            itemEvento.setLuchador2(luchadoresSparse.get(itemEvento.getIdLuchador2()));
                        }

                        // Guardamos los eventos pasados para el filtrado
                        eventsGeneral = new ArrayList<Evento>(Arrays.asList(eventos[0]));

                        adapter = new EventsAdapter(getActivity(), eventos[0], new EventsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Evento evento, int position) {
                                Intent intent = new Intent();
                                intent.setClass(getActivity(), EventActivity.class);
                                intent.putExtra("evento", evento);
                                startActivity(intent);
                            }
                        });
                        swipeRefreshLayout.setRefreshing(false);
                        setLoading(false);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                        setLoading(false);
                        Utilidades.messageWithOk(getActivity(), getView(), getResources().getString(R.string.error_recuperacion_datos));
                    }
                });
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                setLoading(false);
                Utilidades.messageWithOk(getActivity(), getView(), getResources().getString(R.string.error_recuperacion_datos));
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

    private ArrayList<Evento> filter(ArrayList<Evento> events, String texto) {
        ArrayList<Evento> eventosFiltrados = new ArrayList<Evento>();

        if (texto != null)
            texto = texto.toLowerCase();

        // Campos del filtro
        String titulo = "";
        String subtitulo = "";
        String fecha = "";

        if (events != null) {
            for (Evento currentEvent : events) {

                titulo = currentEvent.getTitulo();
                if (titulo != null)
                    titulo = titulo.toLowerCase();

                subtitulo = currentEvent.getSubtitulo();
                if (subtitulo != null)
                    subtitulo = subtitulo.toLowerCase();

                fecha = currentEvent.getFecha();
                if (fecha != null)
                    fecha = fecha.toLowerCase();

                if ((titulo != null && titulo.contains(texto))
                        || (subtitulo != null && subtitulo.contains(texto))
                        || (fecha != null && fecha.contains(texto))) {
                    eventosFiltrados.add(currentEvent);
                }
            }
        }

        return eventosFiltrados;
    }

}
