package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.EventsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FightersFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.News.NewsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    MenuItem menuItem;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(savedInstanceState == null)
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
                return true;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        // menuItem = menu.findItem(R.id.itemBuscador);
        // menuItem = menu.findItem(R.id.itemSettings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSettings:
                DialogSettingsActivity dialogSettings = new DialogSettingsActivity();
                dialogSettings.show(getFragmentManager(),"Settings");
                break;
                default:
                    return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.itemBuscador);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(this);

        // TODO: Solución a esto. Menú lupa solo en ALL
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // adapter.setFilter((Luchador[]) luchadoresGeneral.toArray());
                return true;
            }
        });

        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String texto) {
        try {
            *//*ArrayList<Luchador> listaLuchadoresFiltrados = filter(luchadoresGeneral, texto);
            Luchador[] luchadoresFiltrados = (Luchador[]) listaLuchadoresFiltrados.toArray();
            adapter.setFilter(luchadoresFiltrados);*//*
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }*/
}
