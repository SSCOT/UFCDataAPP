package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.FighterActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.LuchadoresAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FightersListCompleteFragment extends Fragment{

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rvLuchadores)
    RecyclerView recyclerLuchadores;
    @BindView(R.id.etBuscador)
    SearchView etBuscador;

    ArrayList<Luchador> luchadoresGeneral;

    LuchadoresAdapter adapter;
    LuchadorProvider luchadorProvider;

    public static FightersListCompleteFragment newInstance() {
        FightersListCompleteFragment fragment = new FightersListCompleteFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fighters_list_complete, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // Una vez creada la actividad
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        luchadorProvider = new LuchadorProvider(getActivity().getApplicationContext());
        recyclerConf(getView());

        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);

        getAllFighters();

        etBuscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Luchador> listaLuchadoresFiltrados = filter(luchadoresGeneral, newText);
                Luchador[] luchadoresFiltrados = (Luchador[]) listaLuchadoresFiltrados.toArray(new Luchador[listaLuchadoresFiltrados.size()]);
                if(adapter != null)
                    adapter.setFilter(luchadoresFiltrados);

                return true;
            }

            public void callSearch(String query) {

            }

        });
    }

    private void recyclerConf(View view) {
        recyclerLuchadores.setHasFixedSize(true);
        recyclerLuchadores.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerLuchadores.setItemAnimator(new DefaultItemAnimator());
    }

    private void getAllFighters() {
        luchadorProvider.getAll(new LuchadorProvider.LuchadorProviderListener() {
            @Override
            public void onResponse(Luchador[] luchadores) {
                setLoading(false);

                luchadoresGeneral = new ArrayList<Luchador>(Arrays.asList(luchadores));

                adapter = new LuchadoresAdapter(getContext(), luchadores, new LuchadoresAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Luchador luchador, int position) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), FighterActivity.class);
                        intent.putExtra("idLuchador", String.valueOf(luchador.getId()));
                        intent.putExtra("titulo", luchador.getNombre() + " " + luchador.getApellido());

                        startActivity(intent);

                        // Animación
                        /*FragmentActivity activity = getActivity();

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(), getActivity().findViewById(R.id.img), getString(R.string.image_transition));

                        ActivityCompat.startActivity(activity, intent, options.toBundle());*/

                        // startActivity(intent);
                    }
                });
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

    private ArrayList<Luchador> filter(ArrayList<Luchador> luchadores, String texto) {
        ArrayList<Luchador> luchadoresFiltrados = new ArrayList<Luchador>();

        if (texto != null)
            texto = texto.toLowerCase();
        String apellido = "";
        String nombre = "";
        String nick = "";
        String nombreApellido = "";
        String peso = "";

        if (luchadores != null) {
            for (Luchador currentLuchador : luchadores) {

                nombre = currentLuchador.getNombre();
                if (nombre != null)
                    nombre = nombre.toLowerCase();

                apellido = currentLuchador.getApellido();
                if (apellido != null)
                    apellido = apellido.toLowerCase();

                nick = currentLuchador.getNick();
                if (nick != null)
                    nick = nick.toLowerCase();

                if (nombre != null && apellido != null)
                    nombreApellido = String.format("%s %s", nombre, apellido);

                peso = currentLuchador.getCategoria();
                if (peso != null)
                    peso = peso.toLowerCase();

                if ( (nombre != null && nombre.contains(texto))
                        || (apellido != null && apellido.contains(texto))
                        || (nick != null && nick.contains(texto))
                        || (nombreApellido.contains(texto))
                        || (peso != null && peso.contains(texto))) {
                    luchadoresFiltrados.add(currentLuchador);
                }

            }
        }

        return luchadoresFiltrados;
    }
}
