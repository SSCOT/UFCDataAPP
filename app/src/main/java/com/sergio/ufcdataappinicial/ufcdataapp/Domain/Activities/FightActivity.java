package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fights.FightFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

public class FightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Combate fight = (Combate) getIntent().getExtras().getSerializable("combate");

        // Lanzamos el fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment = FightFragment.newInstance(fight);
        commitFragment(ft, fragment, R.id.content);
    }

    private void commitFragment(FragmentTransaction ft, Fragment fragment, int target) {
        ft.replace(target, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}