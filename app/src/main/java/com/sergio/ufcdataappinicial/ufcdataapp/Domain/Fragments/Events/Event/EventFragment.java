package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Fragments.Events.Event;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers.LuchadorProvider;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Alarms.AlarmReceiver;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.sergio.ufcdataappinicial.ufcdataapp.Utilidades;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventFragment extends Fragment {

    Evento evento;
    LuchadorProvider luchadorProvider;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSubtitle)
    TextView txtSubtitle;

    @BindView(R.id.imgLuchador1)
    ImageView imgLuchador1;
    @BindView(R.id.txtLuchador1Altura)
    TextView txtLuchador1Altura;
    @BindView(R.id.txtLuchador1Peso)
    TextView txtLuchador1Peso;
    @BindView(R.id.txtLuchador1Record)
    TextView txtLuchador1Record;
    @BindView(R.id.txtLuchador1Habilidades)
    TextView txtLuchador1Habilidades;

    @BindView(R.id.imgLuchador2)
    ImageView imgLuchador2;
    @BindView(R.id.txtLuchador2Altura)
    TextView txtLuchador2Altura;
    @BindView(R.id.txtLuchador2Peso)
    TextView txtLuchador2Peso;
    @BindView(R.id.txtLuchador2Record)
    TextView txtLuchador2Record;
    @BindView(R.id.txtLuchador2Habilidades)
    TextView txtLuchador2Habilidades;

    private boolean flagWait = false;

    public EventFragment() {
        // Required empty public constructor
    }

    public static EventFragment newInstance(Evento evento) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        args.putSerializable("evento", evento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            evento = (Evento) getArguments().getSerializable("evento");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        luchadorProvider = new LuchadorProvider(getActivity().getApplicationContext());
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        setLoading(true);
        setGeneralData();
        if (evento.getIdLuchador1() > 0 && evento.getIdLuchador2() > 0)
            getData(evento.getIdLuchador1(), evento.getIdLuchador2());
    }

    private void setGeneralData() {
        txtTitle.setText(evento.getTitulo());
        txtSubtitle.setText(evento.getSubtitulo());
    }

    private void getData(int id1, int id2) {
        flagWait = false;
        luchadorProvider.getFighter(String.valueOf(id1), new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador1) {
                // Luchador 1
                if (luchador1.getImgCuerpoIzquierda() != null && luchador1.getImgCuerpoIzquierda() != "")
                    Picasso.get().load(luchador1.getImgCuerpoIzquierda()).placeholder(R.drawable.male_shadow_left).into(imgLuchador1);
                else
                    Picasso.get().load(R.drawable.male_shadow_left).into(imgLuchador1);
                txtLuchador1Record.setText(String.format("%d - %d - %d", luchador1.getWins(), luchador1.getLosses(), luchador1.getDraws()));
                txtLuchador1Altura.setText(luchador1.getAltura());
                txtLuchador1Peso.setText(String.format("%s kg", luchador1.getPeso()));
                txtLuchador1Habilidades.setText(luchador1.getHabilidades());

                if (flagWait)
                    setLoading(false);
                else
                    flagWait = true;
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                Utilidades.messageWithOk(getActivity(), getView(), getResources().getString(R.string.error_recuperacion_datos));
            }
        });
        luchadorProvider.getFighter(String.valueOf(id2), new LuchadorProvider.LuchadorUniqueProviderListener() {
            @Override
            public void onResponse(Luchador luchador2) {
                // Luchador 2
                if (luchador2.getImgCuerpoDerecha() != null && luchador2.getImgCuerpoDerecha() != "")
                    Picasso.get().load(luchador2.getImgCuerpoDerecha()).placeholder(R.drawable.male_shadow_right).into(imgLuchador2);
                else
                    Picasso.get().load(R.drawable.male_shadow_right).into(imgLuchador2);
                txtLuchador2Record.setText(String.format("%d - %d - %d", luchador2.getWins(), luchador2.getLosses(), luchador2.getDraws()));
                txtLuchador2Altura.setText(luchador2.getAltura());
                txtLuchador2Peso.setText(String.format("%s kg", luchador2.getPeso()));
                txtLuchador2Habilidades.setText(luchador2.getHabilidades());
                // Luchador 2
                if (flagWait)
                    setLoading(false);
                else
                    flagWait = true;
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                Utilidades.messageWithOk(getActivity(), getView(), getResources().getString(R.string.error_recuperacion_datos));
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
}
