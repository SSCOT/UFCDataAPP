package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.EventoProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.FightActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.FightsEventosAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventFightsFragment extends Fragment {

    int idEvent;
    EventoProvider eventoProvider;

    @BindView(R.id.rvEventFights)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public EventFightsFragment() {

    }


    public static EventFightsFragment newInstance(int idEvent) {
        EventFightsFragment fragment = new EventFightsFragment();
        Bundle args = new Bundle();
        args.putInt("idEvent", idEvent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idEvent = getArguments().getInt("idEvent");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_fights, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventoProvider = new EventoProvider(getActivity());
        recyclerConf(getView());
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);
        getFights();
    }

    private void getFights() {
        eventoProvider.getFights(idEvent, new EventoProvider.EventoFightsListener() {
            @Override
            public void onResponse(Combate[] fights) {
                final FightsEventosAdapter adapter = new FightsEventosAdapter(getActivity(), fights, new FightsEventosAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Combate fight, int position) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), FightActivity.class);
                        intent.putExtra("combate", fight);
                        startActivity(intent);
                    }
                });
                setLoading(false);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                Utilidades.messageWithOk(getActivity(),getView(),getResources().getString(R.string.error_recuperacion_datos));
            }
        });
    }

    private void recyclerConf(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
