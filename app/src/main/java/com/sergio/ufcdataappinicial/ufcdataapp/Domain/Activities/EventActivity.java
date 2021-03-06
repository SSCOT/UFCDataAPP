package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event.EventFightsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event.EventFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {

    @BindView(R.id.imgEventDetail)
    ImageView imgEventDetail;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Evento event = (Evento) getIntent().getExtras().getSerializable("evento");
        setTitle(event.getTitulo());
        ButterKnife.bind(this);

        setTitleColor();
        if (event.getImgSecundaria() != null && !event.getImgSecundaria().equals(""))
            Picasso.get().load(event.getImgSecundaria()).placeholder(R.drawable.ufc_logo_white).into(imgEventDetail);
        else
            Picasso.get().load(R.drawable.ufc_logo_white).into(imgEventDetail);

        // Lanzamos los datos del luchador
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment = EventFragment.newInstance(event);
        commitFragment(ft, fragment, R.id.content);

        // Lanzamos la lista de combates
        ft = getFragmentManager().beginTransaction();
        fragment = EventFightsFragment.newInstance(event.getId());
        commitFragment(ft, fragment, R.id.fightsContentFragment);
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

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
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
