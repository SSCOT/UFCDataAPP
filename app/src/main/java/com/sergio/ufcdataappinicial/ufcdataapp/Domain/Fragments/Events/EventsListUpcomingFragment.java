package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class EventsListUpcomingFragment extends Fragment {

    public EventsListUpcomingFragment() {
        // Required empty public constructor
    }

    public static EventsListUpcomingFragment newInstance() {
        EventsListUpcomingFragment fragment = new EventsListUpcomingFragment();
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
        return inflater.inflate(R.layout.fragment_events_list_upcoming, container, false);
    }

}
