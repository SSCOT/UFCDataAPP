package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PagerAdapters.EventsPagerAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PagerAdapters.NewsPagerAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsFragment extends Fragment {

    @BindView(R.id.tabLayoutEvents)
    TabLayout tablayout;
    @BindView(R.id.viewPagerEvents)
    ViewPager viewPager;

    private FragmentActivity myContext;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureTabLayout();
        configureViewPager();
    }

    private void configureTabLayout() {
        tablayout.addTab(tablayout.newTab().setText("UPCOMING"));
        tablayout.addTab(tablayout.newTab().setText("PAST"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void configureViewPager() {
        EventsPagerAdapter pagerAdapter = new EventsPagerAdapter(myContext.getSupportFragmentManager(), tablayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                viewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
