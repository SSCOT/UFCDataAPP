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
import android.widget.Toast;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.EventoProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event.EventFightsFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event.EventFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {

    @BindView(R.id.imgEventDetail)
    ImageView imgEventDetail;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    EventoProvider eventoProvider = new EventoProvider(this);

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
        Picasso.with(this).load(event.getImgSecundaria()).into(imgEventDetail);

        // Lanzamos los datos del luchador
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment = EventFragment.newInstance(event);
        commitFragment(ft, fragment, R.id.content);

        // Lanzamos la lista de combates
        ft = getFragmentManager().beginTransaction();
        fragment = EventFightsFragment.newInstance(event.getId());
        commitFragment(ft, fragment, R.id.fightsContentFragment);

        // setLoading(true);
        // getFights(event.getId());
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
