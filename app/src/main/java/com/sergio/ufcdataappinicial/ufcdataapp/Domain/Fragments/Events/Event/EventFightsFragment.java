package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.ButterKnife;

public class EventFightsFragment extends Fragment {

    Combate[] fights;

    public EventFightsFragment() {

    }


    public static EventFightsFragment newInstance(Combate[] fights) {
        EventFightsFragment fragment = new EventFightsFragment();
        Bundle args = new Bundle();
        args.putSerializable("fights", fights);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fights = (Combate[]) getArguments().getSerializable("fights");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_fights, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


}
