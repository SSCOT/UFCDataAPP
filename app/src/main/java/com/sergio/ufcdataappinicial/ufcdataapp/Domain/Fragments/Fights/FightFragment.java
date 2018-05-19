package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Fights;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class FightFragment extends Fragment {

    /*@BindView(R.id.chart)
    StackedBarChart chart;*/
    /*@BindView(R.id.chartBar)
    BarChart barChart;*/
    @BindView(R.id.txtLuchadorNom1)
    TextView txtLuchadorNom1;
    @BindView(R.id.txtLuchadorApe1)
    TextView txtLuchadorApe1;
    @BindView(R.id.imgLuchador1)
    ImageView imgLuchador1;
    @BindView(R.id.txtLuchador1Record)
    TextView txtLuchador1Record;
    @BindView(R.id.txtLuchador1Altura)
    TextView txtLuchador1Altura;
    @BindView(R.id.txtLuchador1Peso)
    TextView txtLuchador1Peso;
    @BindView(R.id.txtLuchador1Reach)
    TextView txtLuchador1Reach;
    @BindView(R.id.pie_view)
    PieView pieView;
    @BindView(R.id.txtStrikingAccuracy1)
    TextView txtStrikingAccuracy1;
    @BindView(R.id.txtGrapplingAccuracy1)
    TextView txtGrapplingAccuracy1;
    @BindView(R.id.txtStrikingDefense1)
    TextView txtStrikingDefense1;
    @BindView(R.id.txtGrapplingDefense1)
    TextView txtGrapplingDefense1;

    @BindView(R.id.txtLuchadorNom2)
    TextView txtLuchadorNom2;
    @BindView(R.id.txtLuchadorApe2)
    TextView txtLuchadorApe2;
    @BindView(R.id.imgLuchador2)
    ImageView imgLuchador2;
    @BindView(R.id.txtLuchador2Record)
    TextView txtLuchador2Record;
    @BindView(R.id.txtLuchador2Altura)
    TextView txtLuchador2Altura;
    @BindView(R.id.txtLuchador2Peso)
    TextView txtLuchador2Peso;
    @BindView(R.id.txtLuchador2Reach)
    TextView txtLuchador2Reach;
    @BindView(R.id.pie_view2)
    PieView pieView2;
    @BindView(R.id.txtStrikingAccuracy2)
    TextView txtStrikingAccuracy2;
    @BindView(R.id.txtGrapplingAccuracy2)
    TextView txtGrapplingAccuracy2;
    @BindView(R.id.txtStrikingDefense2)
    TextView txtStrikingDefense2;
    @BindView(R.id.txtGrapplingDefense2)
    TextView txtGrapplingDefense2;

    @BindView(R.id.win1)
    TextView txtWin1;
    @BindView(R.id.win2)
    TextView txtWin2;

    Combate fight;

    public FightFragment() {
        // Required empty public constructor
    }

    public static FightFragment newInstance(Combate fight) {
        FightFragment fragment = new FightFragment();
        Bundle args = new Bundle();
        args.putSerializable("fight", fight);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fight = (Combate) getArguments().getSerializable("fight");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fight, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData();
    }

    private void setData() {

       if(fight.getGanador1() != null && fight.getGanador1())
           txtWin1.setVisibility(View.VISIBLE);
       else if (fight.getGanador2() != null && fight.getGanador2())
           txtWin2.setVisibility(View.VISIBLE);

        txtLuchadorNom1.setText(fight.getNombre1());
        txtLuchadorApe1.setText(fight.getApellido1());
        if(fight.getImgCuerpo1() != null && !fight.getImgCuerpo1().equals(""))
             Picasso.get().load(fight.getImgCuerpo1()).into(imgLuchador1);
        txtLuchador1Altura.setText(fight.getAltura1());
        txtLuchador1Peso.setText(fight.getPeso1());
        txtLuchador1Reach.setText(fight.getAlcance1());
        txtLuchador1Record.setText(fight.getRecord1());
        txtStrikingAccuracy1.setText(fight.getStrikingAcc1());
        txtStrikingDefense1.setText(fight.getStrikingDef1());
        txtGrapplingAccuracy1.setText(fight.getTakeDownAcc1());
        txtGrapplingDefense1.setText(fight.getTakeDownDef1());

        txtLuchadorNom2.setText(fight.getNombre2());
        txtLuchadorApe2.setText(fight.getApellido2());
        if(fight.getImgCuerpo2() != null && !fight.getImgCuerpo2().equals(""))
             Picasso.get().load(fight.getImgCuerpo2()).into(imgLuchador2);
        txtLuchador2Altura.setText(fight.getAltura2());
        txtLuchador2Peso.setText(fight.getPeso2());
        txtLuchador2Reach.setText(fight.getAlcance2());
        txtLuchador2Record.setText(fight.getRecord2());
        txtStrikingAccuracy2.setText(fight.getStrikingAcc2());
        txtStrikingDefense2.setText(fight.getStrikingDef2());
        txtGrapplingAccuracy2.setText(fight.getTakeDownAcc2());
        txtGrapplingDefense2.setText(fight.getTakeDownDef2());
        
        setCharts();
    }

    private void setCharts() {
        // Chart1
        double total = fight.getF1Draws() + fight.getF1Losses() + fight.getF1Wins();
        double wins = (fight.getF1Wins() * 100) / total;
        double draws = (fight.getF1Draws() * 100) / total;
        double losses = (fight.getF1Losses() * 100) / total;

        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        if (wins > 0)
            if (isAdded()) {
                pieHelperArrayList.add(new PieHelper((float) wins, getResources().getColor(R.color.colorPrimary)));
            }
        if (draws > 0)
            if (isAdded()) {
                pieHelperArrayList.add(new PieHelper((float) draws, getResources().getColor(R.color.colorPrimaryDark)));
            }
        if (losses > 0)
            if (isAdded()) {
                pieHelperArrayList.add(new PieHelper((float) losses, getResources().getColor(R.color.black)));
            }
        pieView.setMinimumHeight(100);
        pieView.setMinimumWidth(100);
        pieView.setDate(pieHelperArrayList);
        pieView.showPercentLabel(true);
        
        // Chart2
        double total2 = fight.getF2Draws() + fight.getF2Losses() + fight.getF2Wins();
        double wins2 = (fight.getF2Wins() * 100) / total2;
        double draws2 = (fight.getF2Draws() * 100) / total2;
        double losses2 = (fight.getF2Losses() * 100) / total2;

        ArrayList<PieHelper> pieHelperArrayList2 = new ArrayList<PieHelper>();
        if (wins > 0)
            if (isAdded()) {
                pieHelperArrayList2.add(new PieHelper((float) wins2, getResources().getColor(R.color.colorPrimary)));
            }
        if (draws > 0)
            if (isAdded()) {
                pieHelperArrayList2.add(new PieHelper((float) draws2, getResources().getColor(R.color.colorPrimaryDark)));
            }
        if (losses > 0)
            if (isAdded()) {
                pieHelperArrayList2.add(new PieHelper((float) losses2, getResources().getColor(R.color.black)));
            }
        pieView2.setMinimumHeight(100);
        pieView2.setMinimumWidth(100);
        pieView2.setDate(pieHelperArrayList2);
        pieView2.showPercentLabel(true);
    }

private void doNothing(){
        /*List<ChartData> value = new ArrayList<>();
        Float[] value1 = {45f, 30f, 16f};
        Float[] value2 = {55f, 70f, 84f};
        //  Float[] value2 = {12f, 0f, 75f};

        ChartData charData1 = new ChartData(value1, "SSC");
        value.add(charData1);
        value.add(new ChartData(value2, "BAD"));

        *//*Float[] value3 = {10f,10f,10f};
        chart.setRange(value3);*//*

        // define xlabels
        List<String> h_lables = new ArrayList<>();
        h_lables.add("KO");
        h_lables.add("STRK");
        h_lables.add("SUB");
        chart.setLabels(h_lables);

        // Set data
        chart.setData(value);

        chart.setPercentageStacked(true);
        chart.setHorizontalStckedBar(true);
        */

        /*ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("January4");
        labels.add("January5");
        labels.add("January4");
        labels.add("January1");
        labels.add("January2");


        IBarDataSet iBarDataSet = null;
        BarData data = new BarData(dataset, iBarDataSet);
        barChart.setData(data);*/
}

}
