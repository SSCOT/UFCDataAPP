package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.EventoProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.EventActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.EventsAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsListPastFragment extends Fragment {

    @BindView(R.id.rvEvents)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarEvents)
    ProgressBar progressBar;

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
        // TODO: Estaría mejor ver si están en local los luchadores y si no están hacer la búsqueda
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

                        final EventsAdapter adapter = new EventsAdapter(getActivity(), eventos[0], new EventsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Evento evento, int position) {
                                Intent intent = new Intent();
                                intent.setClass(getActivity(), EventActivity.class);
                                intent.putExtra("evento", evento);
                                startActivity(intent);
                            }
                        });
                        setLoading(false);
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

            @Override
            public void onErrorResponse(VolleyError error) {

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
