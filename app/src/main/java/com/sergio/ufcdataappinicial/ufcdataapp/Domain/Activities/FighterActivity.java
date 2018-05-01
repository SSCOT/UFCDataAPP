package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class FighterActivity extends AppCompatActivity {

    @BindView(R.id.progressBarFighter)
    ProgressBar progressBar;
    @BindView(R.id.imgLuchadorDetail)
    ImageView imgLuchadorDetail;
    @BindView(R.id.imgLuchadorDetailDescription)
    ImageView imgLuchadorDetailDescription;
    @BindView(R.id.txtFighterDetailName)
    TextView txtFighterDetailName;
    @BindView(R.id.txtFighterDetailRecord)
    TextView txtFighterDetailRecord;
    @BindView(R.id.txtFighterDetailWeightClass)
    TextView txtFighterDetailWeightClass;
    @BindView(R.id.txtFighterDetailHeight)
    TextView txtFighterDetailHeight;
    @BindView(R.id.txtFighterDetailWeight)
    TextView txtFighterDetailWeight;
    @BindView(R.id.txtFighterDetailCity)
    TextView txtFighterDetailCity;
    @BindView(R.id.txtFighterDetailResidence)
    TextView txtFighterDetailResidence;
    @BindView(R.id.txtFighterDetailStrengths)
    TextView getTxtFighterDetailStrengths;

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



        /*ButterKnife.bind(this);

        FighterFragment fighterFragment = FighterFragment.newInstance((String) getIntent().getExtras().get(key));
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentLuchador, fighterFragment);
        // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();*/


    }

    private void getData(String id) {
        fighterProvider.getFighter(id, new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador) {
                setLoading(false);
                setData(luchador);
                setChart2(luchador);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                // TODO: Quitar toast
                Toast.makeText(FighterActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setChart2(Luchador luchador) {
        PieView pieView = (PieView) findViewById(R.id.pie_view);


        double total = (double) luchador.getWins();
        double KO = (double) ((double) (luchador.getWinsKo() * 100) / total);
        double Submissions = (luchador.getWinsSubmission() * 100) / total;
        double Decisions = (luchador.getWinsDecision() * 100) / total;


        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper((float) KO, getResources().getColor(R.color.colorPrimary)));
        pieHelperArrayList.add(new PieHelper((float) Submissions, getResources().getColor(R.color.colorPrimaryDark)));
        pieHelperArrayList.add(new PieHelper((float) Decisions, getResources().getColor(R.color.colorPrimaryExtra)));
        pieView.setMinimumHeight(100);
        pieView.setMinimumWidth(100);
        pieView.setDate(pieHelperArrayList);
        pieView.showPercentLabel(true); //optional
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
        getTxtFighterDetailStrengths.setText(luchador.getHabilidades());
        Picasso.with(this).load(luchador.getImgPerfil()).into(imgLuchadorDetail);
        Picasso.with(this).load(luchador.getImgCuerpoIzquierda()).into(imgLuchadorDetailDescription);

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






















    /*public void setActionBarTitle(String title) {
        setTitle("ANDAAAAA");
        Toast.makeText(this, "M", Toast.LENGTH_SHORT).show();
    }*/


}
