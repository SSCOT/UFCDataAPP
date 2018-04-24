package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class MediaVideoEmbeddedFragment extends Fragment {

    public static MediaVideoEmbeddedFragment newInstance(Media mediaItem) {
        MediaVideoEmbeddedFragment articleFragment = new MediaVideoEmbeddedFragment();
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
        View view = inflater.inflate(R.layout.fragment_media_embeddedvideo, container, false);

        Media mediaItem = (Media) getArguments().get("mediaItem");

        if (getArguments() != null) {
            mediaItem = (Media) getArguments().get("mediaItem");
        }

        // setWeb(view, url);
        return view;
    }
}
