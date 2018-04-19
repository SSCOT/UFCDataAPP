package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PagerAdapters.FightersPagerAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PagerAdapters.NewsPagerAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class FightersFragment extends Fragment {

    private FragmentActivity myContext;

    public static FightersFragment newInstance() {
        FightersFragment fragment = new FightersFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fighters, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TabLayout tablayout = getView().findViewById(R.id.tabLayoutFighters);
        tablayout.addTab(tablayout.newTab().setText("ALL"));
        tablayout.addTab(tablayout.newTab().setText("CHAMPIONS"));
        tablayout.addTab(tablayout.newTab().setText("CATEGORIES"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = getView().findViewById(R.id.viewPagerFighters);
        FightersPagerAdapter pagerAdapter = new FightersPagerAdapter(myContext.getSupportFragmentManager(), tablayout.getTabCount());

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
