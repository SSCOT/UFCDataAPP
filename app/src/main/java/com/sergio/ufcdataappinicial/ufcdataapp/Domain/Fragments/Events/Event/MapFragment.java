package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.ButterKnife;

public class MapFragment extends Fragment {

    Evento evento;

    public MapFragment() {

    }

    public static MapFragment newInstance(Evento evento) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putSerializable("evento", evento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            evento = (Evento) getArguments().getSerializable("evento");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_map, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
