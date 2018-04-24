package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PagerAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.EventsListPastFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.EventsListUpcomingFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersListChampionsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersListCompleteFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersListWeightFragment;

public class EventsPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public EventsPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new EventsListUpcomingFragment();
            case 1:
                return new EventsListPastFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
