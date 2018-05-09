package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.Fight.FighterFightListFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.FighterFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FighterActivity extends AppCompatActivity {

    @BindView(R.id.imgLuchadorDetail)
    ImageView imgLuchadorDetail;
    @BindView(R.id.progressBarFighter)
    ProgressBar progressBar;

    LuchadorProvider fighterProvider = new LuchadorProvider(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getExtras().getString("titulo"));

        String idLuchador = getIntent().getExtras().getString("idLuchador");
        ButterKnife.bind(this);

        setLoading(true);
        getData(idLuchador);
    }

    private void commitFragment(FragmentTransaction ft, Fragment fragment, int target) {
        ft.replace(target, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void setFighterImage(String url) {
        Picasso.with(this).load(url).into(imgLuchadorDetail);
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

    private void getData(String id) {
        fighterProvider.getFighter(id, new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador) {
                setLoading(false);
                /*setData(luchador);
                setChart(luchador);*/
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
                // TODO: Quitar toast
                // Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    /*private void getData(String id) {
        fighterProvider.getFighter(id, new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador) {
                setLoading(false);
                setData(luchador);
                setChart(luchador);
                // Lanzamos la lista de combates
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment fragment = FighterFightListFragment.newInstance(luchador);
                commitFragment(ft, fragment);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                // TODO: Quitar toast
                Toast.makeText(FighterActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setChart(Luchador luchador) {
        PieView pieView = (PieView) findViewById(R.id.pie_view);


        double total = luchador.getWinsKo() + luchador.getWinsDecision() + luchador.getWinsSubmission();
        double KO = (luchador.getWinsKo() * 100) / total;
        double Submissions = (luchador.getWinsSubmission() * 100) / total;
        double Decisions = (luchador.getWinsDecision() * 100) / total;


        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        if (KO > 0)
            pieHelperArrayList.add(new PieHelper((float) KO, getResources().getColor(R.color.colorPrimary)));
        if (Submissions > 0)
            pieHelperArrayList.add(new PieHelper((float) Submissions, getResources().getColor(R.color.colorPrimaryDark)));
        if (Decisions > 0)
            pieHelperArrayList.add(new PieHelper((float) Decisions, getResources().getColor(R.color.black)));
        pieView.setMinimumHeight(100);
        pieView.setMinimumWidth(100);
        pieView.setDate(pieHelperArrayList);
        pieView.showPercentLabel(true);
    }

    private void setData(Luchador luchador) {
        txtFighterDetailName.setText(String.format("%s %s", luchador.getNombre(), luchador.getApellido()));
        txtFighterDetailHeight.setText(luchador.getAltura());
        txtFighterDetailCity.setText(luchador.getResidenciaCiudad());
        if (luchador.getResidenciaEstado() != null)
            txtFighterDetailResidence.setText(String.format("%s, %s", luchador.getResidenciaEstado(), luchador.getResidenciaPais()));
        else
            txtFighterDetailResidence.setText(luchador.getResidenciaPais());
        txtFighterDetailRecord.setText(String.format("%d - %d - %d", luchador.getWins(), luchador.getLosses(), luchador.getDraws()));
        txtFighterDetailWeight.setText(String.format("%s kg", luchador.getPeso()));
        txtFighterDetailWeightClass.setText(luchador.getCategoria());
        if (luchador.getCampeon()) {
            txtFighterDetailWeightClass.setBackgroundColor(getResources().getColor(R.color.gold));
            txtFighterDetailWeightClass.setPadding(5, 5, 5, 5);
            txtFighterDetailWeightClass.setTextColor(getResources().getColor(R.color.white));
        }


        getTxtFighterDetailStrengths.setText(luchador.getHabilidades());
        Picasso.with(this).load(luchador.getImgPerfil()).into(imgLuchadorDetail);
        Picasso.with(this).load(luchador.getImgCuerpoIzquierda()).into(imgLuchadorDetailDescription);

    }



    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void commitFragment(FragmentTransaction ft, Fragment fragment) {
        ft.replace(R.id.fightsContentFragment, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }*/
}
