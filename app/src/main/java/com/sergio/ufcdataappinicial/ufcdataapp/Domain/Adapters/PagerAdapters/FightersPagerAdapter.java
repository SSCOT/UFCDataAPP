package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PagerAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersListChampionsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersListCompleteFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersListWeightFragment;


public class FightersPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public FightersPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FightersListCompleteFragment();
            case 1:
                return new FightersListChampionsFragment();
            case 2:
                return new FightersListWeightFragment();
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
