package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;

import android.app.Activity;
import android.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaVideoInternalFragment extends Fragment {

    @BindView(R.id.videoView)
    VideoView videoView;

    String link = "";

    public static MediaVideoInternalFragment newInstance(String link) {
        MediaVideoInternalFragment articleFragment = new MediaVideoInternalFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("link", link);
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
        View view = inflater.inflate(R.layout.fragment_media_internalvideo, container, false);
        ButterKnife.bind(this,view);

        if (getArguments() != null) {
            link = (String) getArguments().get("link");
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MediaController mc = new MediaController(getActivity());
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(link));
        videoView.start();
    }

}
