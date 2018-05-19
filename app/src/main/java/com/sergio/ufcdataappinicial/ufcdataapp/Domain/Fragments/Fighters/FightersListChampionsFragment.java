package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
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

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Prueba;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.bbdd.UfcDatabase;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.FighterActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.ChampionsAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.LuchadoresAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FightersListChampionsFragment extends Fragment {

    private static ChampionsAdapter adapter;
    private static Context context;

    @BindView(R.id.progressBarChampions)
    ProgressBar progressBar;
    @BindView(R.id.rvChampions)
    RecyclerView recyclerLuchadores;

    LuchadorProvider luchadorProvider;

    public static FightersListChampionsFragment newInstance() {
        FightersListChampionsFragment fragment = new FightersListChampionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fighters_list_champions, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        luchadorProvider = new LuchadorProvider(getActivity().getApplicationContext());
        recyclerConf(getView());
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        context = getContext();

        setLoading(true);
        getChampions();
    }

    private void recyclerConf(View view) {
        recyclerLuchadores.setHasFixedSize(true);
        recyclerLuchadores.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerLuchadores.setItemAnimator(new DefaultItemAnimator());
    }

    private void getChampions() {
        luchadorProvider.getChampions(new LuchadorProvider.LuchadorProviderListener() {
            @Override
            public void onResponse(Luchador[] luchadores) {
                setLoading(false);
                adapter = new ChampionsAdapter(getActivity(), luchadores, new ChampionsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Luchador luchador, int position) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), FighterActivity.class);
                        intent.putExtra("idLuchador", String.valueOf(luchador.getId()));
                        intent.putExtra("titulo", luchador.getNombre() + " " + luchador.getApellido());
                        startActivity(intent);
                    }
                });
                recyclerLuchadores.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                Utilidades.messageWithOk(getActivity(),getView(),getResources().getString(R.string.error_recuperacion_datos));
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
