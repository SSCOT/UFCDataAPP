package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.R;


public class NewsListMediaFragment extends Fragment {


    public NewsListMediaFragment() {
        // Required empty public constructor
    }

    public static NewsListMediaFragment newInstance() {
        NewsListMediaFragment fragment = new NewsListMediaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list_media, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}