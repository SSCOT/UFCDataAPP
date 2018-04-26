package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.EventsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersListCompleteFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.NewsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    Luchador[] luchadoresMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setFragment("news");

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btnNavFighters:
                        setFragment("fighters");
                        break;
                    case R.id.btnNavNews:
                        setFragment("news");
                        break;
                    case R.id.btnNavEvents:
                        setFragment("events");
                        break;
                }

                return false;
            }
        });
    }

    private void setFragment(String tipo) {

        Fragment fragment = null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        switch (tipo) {
            case "fighters":
                fragment = FightersFragment.newInstance();
                break;
            case "news":
                fragment = NewsFragment.newInstance();
                break;
            case "events":
                fragment = EventsFragment.newInstance();
                break;
        }

        if (fragment == null) {
            return;
        }

        commitFragment(ft, fragment);
    }

    private void commitFragment(FragmentTransaction ft, Fragment fragment) {
        ft.replace(R.id.fragmentPrincipal, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.menu_buscador, menu);
        MenuItem menuItem = menu.findItem(R.id.itemBuscador);
        SearchView searchView = (SearchView) menuItem.getActionView();*/
        return super.onCreateOptionsMenu(menu);
    }
}
