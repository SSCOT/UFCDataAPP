package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.MediaPhotoGalleryFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.MediaVideoEmbeddedFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.MediaVideoInternalFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class MediaActivity extends AppCompatActivity {

    public static Media mediaItem = null;
    /*public static final String tipoMedia = "tipoMedia";
    public static final String idMedia = "idMedia";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        mediaItem = (Media) getIntent().getSerializableExtra("mediaItem");

        setFragment(mediaItem.getTipo());

    }

    private void setFragment(String tipo){

        Fragment fragment = null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        switch (tipo){
            case "PHOTOGALLERY":
                fragment = MediaPhotoGalleryFragment.newInstance(mediaItem);
                break;
            case "INTERNALVIDEO":
                fragment = MediaVideoInternalFragment.newInstance(mediaItem);
                break;
            case "EMBEDDEDVIDEO":
                fragment = MediaVideoEmbeddedFragment.newInstance(mediaItem);
                break;
        }

        if (fragment == null){
            return;
        }

        commitFragment(ft, fragment);
    }

    private void commitFragment(FragmentTransaction ft, Fragment fragment) {
        ft.replace(R.id.fragmentPrincipalMedia, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
