package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.PagerAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.NewsListArticlesFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.NewsListMediaFragment;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public NewsPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new NewsListArticlesFragment();
            case 1:
                return new NewsListMediaFragment();
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
