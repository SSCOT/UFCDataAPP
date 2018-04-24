package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class MediaPhotoGalleryFragment extends Fragment {

    public static MediaPhotoGalleryFragment newInstance(Media mediaItem) {
        MediaPhotoGalleryFragment articleFragment = new MediaPhotoGalleryFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("mediaItem", mediaItem);
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

        Media mediaItem = (Media) getArguments().get("mediaItem");

        if (getArguments() != null) {
            mediaItem = (Media) getArguments().get("mediaItem");
        }

        // setWeb(view, url);
        return view;
    }

    /*public void setWeb(View view, String url) {
        WebView webView = view.findViewById(R.id.articleWeb);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }*/

}
