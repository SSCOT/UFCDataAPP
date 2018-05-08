package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.FighterActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.Fight.FightListFragment;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class FighterFragment extends Fragment {

    LuchadorProvider fighterProvider;

    @BindView(R.id.progressBarFighter)
    ProgressBar progressBar;
    /*@BindView(R.id.imgLuchadorDetail)
    ImageView imgLuchadorDetail;*/
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
    @BindView(R.id.pie_view)
    PieView pieView;


    public static FighterFragment newInstance(String idLuchador) {
        FighterFragment fragment = new FighterFragment();
        Bundle arguments = new Bundle();
        arguments.putString("luchador", idLuchador);
        fragment.setArguments(arguments);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fighter, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fighterProvider = new LuchadorProvider(getActivity().getApplicationContext());
        setLoading(true);
        // Toolbar
        // ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getActivity().setTitle("PACO MARTINEZ SORIA");
        String idLuchador = (String) getArguments().get("luchador");
        getData(idLuchador);
    }

    private void getData(String id) {
        fighterProvider.getFighter(id, new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador) {
                setLoading(false);
                setData(luchador);
                setChart(luchador);
                ((FighterActivity) getActivity()).setFighterImage(luchador.getImgPerfil());
                // Lanzamos la lista de combates
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment fragment = FightListFragment.newInstance(luchador);
                commitFragment(ft, fragment);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                // TODO: Quitar toast
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setChart(Luchador luchador) {
        // PieView pieView = (PieView) findViewById(R.id.pie_view);


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
        // Picasso.with(getActivity()).load(luchador.getImgPerfil()).into(imgLuchadorDetail);
        Picasso.with(getActivity()).load(luchador.getImgCuerpoIzquierda()).into(imgLuchadorDetailDescription);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
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

    private void commitFragment(FragmentTransaction ft, Fragment fragment) {
        ft.replace(R.id.fightsContentFragment, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }


}
