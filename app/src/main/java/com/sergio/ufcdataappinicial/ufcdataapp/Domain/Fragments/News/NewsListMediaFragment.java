package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.MediaProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.MediaActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.MediaAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListMediaFragment extends Fragment {

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.progressBarMedia)
    ProgressBar progressBar;
    @BindView(R.id.rvMedia)
    RecyclerView recycler;

    MediaProvider mediaProvider;

    Media[] arrayMedia;

    public NewsListMediaFragment() {
    }

    public static NewsListMediaFragment newInstance() {
        NewsListMediaFragment fragment = new NewsListMediaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_list_media, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mediaProvider = new MediaProvider(getActivity().getApplicationContext());
        recyclerConf();
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);
        getMedia();

        // Swipe refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMedia();
            }
        });
    }

    private void recyclerConf() {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler.setItemAnimator(new DefaultItemAnimator());
    }

    private void getMedia() {
        mediaProvider.getMedia(new MediaProvider.MediaProviderListener() {
            @Override
            public void onResponse(Media[] response) {
                arrayMedia = sortResponse(response);
                swipeRefreshLayout.setRefreshing(false);
                setLoading(false);
                MediaAdapter adapter = new MediaAdapter(getActivity(), arrayMedia, new MediaAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Media mediaItem, int position) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), MediaActivity.class);
                        intent.putExtra("mediaItem", arrayMedia[position]);
                        startActivity(intent);
                    }
                });
                recycler.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                setLoading(false);
                Utilidades.messageWithOk(getContext(), getView(), getResources().getString(R.string.error_recuperacion_datos));
            }
        });
    }

    private Media[] sortResponse(Media[] response) {

        SparseArray<Media> arrayMedia = new SparseArray<>();
        ArrayList<Media> arrayMediaAux = new ArrayList<>();

        /*
            Ordenamos por fecha. La mejor forma es obtener la fecha (string) y liberarla de
            caracteres de separación. Luego la transformamos en entero y la metemos como key en un
            SparseArray. En el value de cada key metemos el elemento Media.
            Con el método append, nos queda ordenado pero de menor a mayor.

         */
        for (Media item : response) {
            String fecha = item.getFecha();
            if (fecha != null && !fecha.equals("")) {
                String dia = fecha.substring(0, 10).replace("-", "");
                int fechaNumero = Integer.parseInt(dia);
                if (fechaNumero > 0)
                    arrayMedia.append(fechaNumero, item);
            }
        }

        /*
            Con la ayuda de un array list volteamos el orden del SparseArray
         */

        for (int i = arrayMedia.size() - 1; i >= 0; i--) {
            int key = arrayMedia.keyAt(i);
            Media element = arrayMedia.get(key);
            arrayMediaAux.add(element);
        }

        /*
            Finalmente realizamos una conversión a array normal para facilitar el tratamiento de los
            datos.
         */

        return arrayMediaAux.toArray(new Media[arrayMediaAux.size()]);
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
