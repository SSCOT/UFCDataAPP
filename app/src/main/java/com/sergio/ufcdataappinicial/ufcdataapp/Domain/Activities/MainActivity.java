package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters.LuchadoresAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.FighterListFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setFragment("fighters");

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btnNavFighters:
                        setFragment("fighters");
                        break;
                    case R.id.btnNavNews:
                        setFragment("fighters");
                        break;
                    case R.id.btnNavEvents:
                        setFragment("fighters");
                        break;
                }

                return false;
            }
        });
    }

    private void setFragment(String tipo){

        Fragment fragment = null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        switch (tipo){
            case "fighters":
                fragment = FighterListFragment.newInstance();
                break;
            case "news":
                fragment = FighterListFragment.newInstance();
                break;
            case "events":
                fragment = FighterListFragment.newInstance();
                break;
        }

        if (fragment == null){
            return;
        }

        ft.replace(R.id.fFighters, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
