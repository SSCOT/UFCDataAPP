package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;


import android.content.Intent;
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
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.MediaProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.MediaActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.MediaAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListMediaFragment extends Fragment {

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
        setLoading(true);
        getMedia();
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
                setLoading(false);
                Toast.makeText(getActivity(), "Error al recoger los datos", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Media[] sortResponse(Media[] response) {

        for (int i = 1; i < response.length; i++) {

            // if (Integer.parseInt(response[i].getFecha().substring(0,10).replace("-","")) < 5){

            Media aux;

            String fecha1 = response[i].getFecha();
            String fecha2 = response[i-1].getFecha();

            if(fecha1 == "" || fecha1 == null)
                fecha1 = "0000-00-00";
            if(fecha2 == "" || fecha2 == null)
                fecha2 = "0000-00-00";

            while (Integer.parseInt(fecha1.substring(0,9).replace("-","")) < Integer.parseInt(fecha2.substring(0,9).replace("-",""))) {
                aux = response[i];
                response[i] = response[i-1];
                response[i-1] = aux;

                fecha1 = response[i].getFecha();
                fecha2 = response[i-1].getFecha();
                if(fecha1 == "" || fecha1 == null)
                    fecha1 = "0000-00-00";
                if(fecha2 == "" || fecha2 == null)
                    fecha2 = "0000-00-00";
            }

        }

        return response;
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
