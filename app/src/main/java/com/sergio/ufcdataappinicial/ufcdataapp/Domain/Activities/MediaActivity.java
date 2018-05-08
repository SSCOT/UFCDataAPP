package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.MediaPhotoGalleryFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.MediaVideoInternalFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class MediaActivity extends AppCompatActivity {

    public static Media mediaItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        mediaItem = (Media) getIntent().getSerializableExtra("mediaItem");

        setFragment(mediaItem.getTipo());

    }

    private void setFragment(String tipo) {

        Fragment fragment = null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        switch (tipo) {
            case "PHOTOGALLERY":
                fragment = MediaPhotoGalleryFragment.newInstance(mediaItem.getId());
                break;
            case "INTERNALVIDEO":
                String link = String.format(BuildConfig.API_URL_INTERNAL_VIDEO, mediaItem.getUrlInterna());
                fragment = MediaVideoInternalFragment.newInstance(link);
                break;
            case "EMBEDDEDVIDEO":
                if (mediaItem.getTipoEmbebido().equals("YOUTUBEVIDEO")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + mediaItem.getIdEmbebido())));
                } else {
                    // TODO: Sustituir toast
                    Toast.makeText(this, "No se ha podido reproducir el vídeo", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        if (fragment == null) {
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
