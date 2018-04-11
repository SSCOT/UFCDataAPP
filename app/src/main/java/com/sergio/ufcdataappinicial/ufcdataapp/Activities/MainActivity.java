package com.sergio.ufcdataappinicial.ufcdataapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Adapters.LuchadoresAdapter;
import com.sergio.ufcdataappinicial.ufcdataapp.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.NetworkProvider.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.NetworkProvider.RequestManager;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvLuchadores)
    RecyclerView recyclerLuchadores;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerConf();
        getData();

    }

    private void getData() {
        setLoading(true);

        GsonRequest gsonRequest = new GsonRequest<>("http://ufc-data-api.ufc.com/api/v3/iphone/fighters", Luchador[].class, null, new Response.Listener<Luchador[]>() {

            @Override
            public void onResponse(Luchador[] luchadores) {
                setLoading(false);
                LuchadoresAdapter adapter = new LuchadoresAdapter(MainActivity.this, luchadores);
                recyclerLuchadores.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
            }
        });

        RequestManager.getInstance().addToRequestQueue(this, gsonRequest);
    }

    private void recyclerConf(){
        recyclerLuchadores.setHasFixedSize(true);
        recyclerLuchadores.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerLuchadores.setItemAnimator(new DefaultItemAnimator());
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
