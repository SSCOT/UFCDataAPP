package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.ChampionsAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.LuchadoresAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class FightersListChampionsFragment extends Fragment {

    ProgressBar progressBar;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fighters_list_champions, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        luchadorProvider = new LuchadorProvider(getActivity().getApplicationContext());
        progressBar = getView().findViewById(R.id.progressBarChampions);
        recyclerConf(getView());
        setLoading(true);
        getChampions();
    }

    private void recyclerConf(View view) {
        recyclerLuchadores = view.findViewById(R.id.rvChampions);
        recyclerLuchadores.setHasFixedSize(true);
        recyclerLuchadores.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerLuchadores.setItemAnimator(new DefaultItemAnimator());
    }

    private void getChampions() {
        luchadorProvider.getChampions(new LuchadorProvider.LuchadorProviderListener() {
            @Override
            public void onResponse(Luchador[] luchadores) {
                setLoading(false);
                ChampionsAdapter adapter = new ChampionsAdapter(getActivity(), luchadores);
                recyclerLuchadores.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
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
