package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class FightersListChampionsFragment extends Fragment {

    public static FightersListChampionsFragment newInstance() {
        FightersListChampionsFragment fragment = new FightersListChampionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fighters_list_champions, container, false);
    }

}
