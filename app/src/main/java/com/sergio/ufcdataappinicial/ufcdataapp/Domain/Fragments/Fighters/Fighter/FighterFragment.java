package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fighters.Fighter;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class FighterFragment extends Fragment {

    LuchadorProvider fighterProvider;

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

    public static FighterFragment newInstance(Luchador luchador) {
        FighterFragment fragment = new FighterFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("luchador", luchador);
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
        Luchador luchador = (Luchador) getArguments().get("luchador");
        setData(luchador);
        setChart(luchador);
    }

    private void setChart(Luchador luchador) {

        double total = luchador.getWinsKo() + luchador.getWinsDecision() + luchador.getWinsSubmission();
        double KO = (luchador.getWinsKo() * 100) / total;
        double Submissions = (luchador.getWinsSubmission() * 100) / total;
        double Decisions = (luchador.getWinsDecision() * 100) / total;


        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        if (KO > 0)
            if (isAdded()) {
                pieHelperArrayList.add(new PieHelper((float) KO, getResources().getColor(R.color.colorPrimary)));
            }
        if (Submissions > 0)
            if (isAdded()) {
                pieHelperArrayList.add(new PieHelper((float) Submissions, getResources().getColor(R.color.colorPrimaryDark)));
            }
        if (Decisions > 0)
            if (isAdded()) {
                pieHelperArrayList.add(new PieHelper((float) Decisions, getResources().getColor(R.color.black)));
            }
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
         Picasso.get().load(luchador.getImgCuerpoIzquierda()).into(imgLuchadorDetailDescription);

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
}
