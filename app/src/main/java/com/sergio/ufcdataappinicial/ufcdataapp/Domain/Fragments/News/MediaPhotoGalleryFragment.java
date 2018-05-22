package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Foto;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.MediaProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.PhotoActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PictureAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaPhotoGalleryFragment extends Fragment {

    MediaProvider mediaProvider;
    int idMediaItem;

    @BindView(R.id.progressBarGallery)
    ProgressBar progressBar;
    @BindView(R.id.rvFotos)
    RecyclerView recycler;

    Foto[] fotos;

    public static MediaPhotoGalleryFragment newInstance(int idMediaItem) {
        MediaPhotoGalleryFragment articleFragment = new MediaPhotoGalleryFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("idMediaItem", idMediaItem);
        articleFragment.setArguments(arguments);
        return articleFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_photogallery, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            idMediaItem = (int) getArguments().get("idMediaItem");
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mediaProvider = new MediaProvider(getActivity().getApplicationContext());
        recyclerConf();
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);
        getGallery();
    }

    private void recyclerConf() {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recycler.setItemAnimator(new DefaultItemAnimator());
    }

    private void getGallery() {
        mediaProvider.getMediaItem(idMediaItem, new MediaProvider.MediaDetailProviderListener() {
            @Override
            public void onResponse(Media mediaItem) {
                fotos = mediaItem.getFotos();
                setLoading(false);
                PictureAdapter adapter = new PictureAdapter(getActivity(), fotos, new PictureAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Foto foto, int position) {
                        Toast.makeText(getActivity(), foto.getDescripcion(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), PhotoActivity.class);
                        intent.putExtra("url", foto.getImagen());
                        startActivity(intent);
                    }
                });
                /*MediaAdapter adapter = new MediaAdapter(getActivity(), fotos, new MediaAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Media mediaItem, int position) {

                        *//*Intent intent = new Intent();
                        intent.setClass(getActivity(), MediaActivity.class);
                        intent.putExtra("mediaItem", arrayMedia[position]);
                        startActivity(intent);*//*
                    }
                });*/
                recycler.setAdapter(adapter);
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
