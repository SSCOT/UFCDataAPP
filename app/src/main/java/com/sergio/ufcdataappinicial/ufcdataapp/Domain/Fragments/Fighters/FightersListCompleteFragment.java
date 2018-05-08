package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
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

public class FightersListCompleteFragment extends Fragment implements SearchView.OnQueryTextListener {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rvLuchadores)
    RecyclerView recyclerLuchadores;
    @BindView(R.id.etBuscador)
    EditText etBuscador;

    ArrayList<Luchador> luchadoresGeneral;

    LuchadoresAdapter adapter;
    LuchadorProvider luchadorProvider;

    public static FightersListCompleteFragment newInstance() {
        FightersListCompleteFragment fragment = new FightersListCompleteFragment();
        return fragment;
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
        setLoading(true);
        getAllFighters();
        etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")  && adapter != null) {
                    ArrayList<Luchador> listaLuchadoresFiltrados = filter(luchadoresGeneral, charSequence.toString());
                    Luchador[] luchadoresFiltrados = (Luchador[]) listaLuchadoresFiltrados.toArray(new Luchador[listaLuchadoresFiltrados.size()]);adapter.setFilter(luchadoresFiltrados);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_buscador, menu);
        MenuItem menuItem = menu.findItem(R.id.itemBuscador);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(this);

        // TODO: Solución a esto. Menú lupa solo en ALL
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.setFilter((Luchador[]) luchadoresGeneral.toArray());
                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String texto) {
        try {
            ArrayList<Luchador> listaLuchadoresFiltrados = filter(luchadoresGeneral, texto);
            Luchador[] luchadoresFiltrados = (Luchador[]) listaLuchadoresFiltrados.toArray();
            adapter.setFilter(luchadoresFiltrados);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    private ArrayList<Luchador> filter(ArrayList<Luchador> luchadores, String texto) {
        ArrayList<Luchador> luchadoresFiltrados = new ArrayList<Luchador>();

        if (texto != null)
            texto = texto.toLowerCase();
        String apellido = "";
        String nombre = "";
        String nick = "";
        String nombreApellido = "";

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

                if (nombre != null && nombre.contains(texto)) {
                    luchadoresFiltrados.add(currentLuchador);
                } else if (apellido != null && apellido.contains(texto)) {
                    luchadoresFiltrados.add(currentLuchador);
                } else if (nick != null && nick.contains(texto)) {
                    luchadoresFiltrados.add(currentLuchador);
                } else if (nombreApellido.contains(texto)) {
                    luchadoresFiltrados.add(currentLuchador);
                }
            }
        }

        return luchadoresFiltrados;
    }
}
