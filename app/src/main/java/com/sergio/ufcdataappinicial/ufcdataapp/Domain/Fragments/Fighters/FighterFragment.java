package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FighterFragment extends Fragment {

    LuchadorProvider fighterProvider;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgLuchadorDetail)
    ImageView imgLuchadorDetail;
    @BindView(R.id.imgLuchadorDetailDescription)
    ImageView imgLuchadorDetailDescription;

    @BindView(R.id.progressBarFighter)
    ProgressBar progressBar;


    public static FighterFragment newInstance(String id) {
        FighterFragment fragment = new FighterFragment();
        Bundle arguments = new Bundle();
        arguments.putString("luchador", id);
        fragment.setArguments(arguments);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fighter, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fighterProvider = new LuchadorProvider(getActivity().getApplicationContext());
        setLoading(true);
        // Toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle("PACO MARTINEZ SORIA");
        String idLuchador = (String) getArguments().get("luchador");
        getData(idLuchador);
    }

    private void getData(String id) {
        fighterProvider.getFighter(id, new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador) {
                setLoading(false);
                setData(luchador);
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    private void setData(Luchador luchador) {
        getActivity().setTitle(luchador.getNombre() + " " + luchador.getApellido());
        Picasso.with(getActivity()).load(luchador.getImgPerfil()).into(imgLuchadorDetail);
        Picasso.with(getActivity()).load(luchador.getImgCuerpoIzquierda()).into(imgLuchadorDetailDescription);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
            getFragmentManager().popBackStack();
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
