package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.Fight;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.LuchadorCombate;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.FightsLuchadorAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FightListFragment extends Fragment {

    @BindView(R.id.rvFightsLuchador)
    RecyclerView recyclerView;

    private Luchador luchador;
    private LuchadorProvider luchadorProvider;

    public static FightListFragment newInstance(Luchador luchador) {
        FightListFragment fragment = new FightListFragment();
        Bundle args = new Bundle();
        args.putSerializable("luchador", luchador);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            luchador = (Luchador) getArguments().getSerializable("luchador");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fight_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        luchadorProvider = new LuchadorProvider(getActivity().getApplicationContext());
        recyclerConf(getView());

        LuchadorCombate[] cmbts = luchador.getCombates();
        if(cmbts.length > 20)
            cmbts = Arrays.copyOfRange(cmbts, 0, 20);

        FightsLuchadorAdapter adapter = new FightsLuchadorAdapter(getActivity().getApplicationContext(), cmbts);
        recyclerView.setAdapter(adapter);
    }

    private void recyclerConf(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
