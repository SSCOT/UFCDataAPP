package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.Fighter.FighterFightListFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.Fighter.FighterFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FighterActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView imgLuchadorDetail;
    @BindView(R.id.progressBarFighter)
    ProgressBar progressBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    LuchadorProvider fighterProvider = new LuchadorProvider(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighter);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getIntent().getExtras().getString("titulo"));
        String idLuchador = getIntent().getExtras().getString("idLuchador");

        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);
        setTitleColor();
        getData(idLuchador);
    }

    private void getData(String id) {
        fighterProvider.getFighter(id, new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador) {
                setLoading(false);
                setFighterImage(luchador.getImgPerfil());

                // Lanzamos los datos del luchador
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment fragment = FighterFragment.newInstance(luchador);
                commitFragment(ft, fragment, R.id.content);

                // Lanzamos la lista de combates
                ft = getFragmentManager().beginTransaction();
                fragment = FighterFightListFragment.newInstance(luchador);
                commitFragment(ft, fragment, R.id.fightsContentFragment);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                Utilidades.messageWithOk(FighterActivity.this,findViewById(R.id.content),getResources().getString(R.string.error_recuperacion_datos));
            }
        });
        // setLoading(false);
    }

    private void setTitleColor() {
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
    }

    private void commitFragment(FragmentTransaction ft, Fragment fragment, int target) {
        ft.replace(target, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void setFighterImage(String url) {
        Picasso.get().load(url).into(imgLuchadorDetail);
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

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
