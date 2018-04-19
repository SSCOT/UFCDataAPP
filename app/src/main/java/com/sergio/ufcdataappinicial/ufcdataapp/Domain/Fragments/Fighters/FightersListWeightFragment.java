package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class FightersListWeightFragment extends Fragment {

    public static FightersListWeightFragment newInstance() {
        FightersListWeightFragment fragment = new FightersListWeightFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fighters_list_weight, container, false);
    }

}
