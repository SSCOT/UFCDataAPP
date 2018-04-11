package com.sergio.ufcdataappinicial.ufcdataapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import butterknife.ButterKnife;

public class LuchadoresAdapter extends RecyclerView.Adapter<LuchadoresAdapter.LuchadoresViewHolder> {

    private Context context;
    private Luchador[] luchadores;



    public LuchadoresAdapter(Context context, Luchador[] luchadores) {
        this.context = context;
        this.luchadores = luchadores;
    }

    // Asignamos la celda y la inflamos
    @Override
    public LuchadoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ButterKnife.bind(parent);

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luchador, parent, false);

        return new LuchadoresViewHolder(context, item);
    }


    @Override
    public void onBindViewHolder(LuchadoresViewHolder viewHolder, int position) {
        Luchador luchador = luchadores[position];
        viewHolder.bindLuchador(luchador);

    }

    // Número de elementos
    @Override
    public int getItemCount() {
        return luchadores.length;
    }


    public static class LuchadoresViewHolder extends RecyclerView.ViewHolder {

        private TextView txt;

        public LuchadoresViewHolder(Context context, View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.txtLuchador1);
        }

        public void bindLuchador(Luchador luchador) {
            txt.setText(luchador.getNombre() + " " +luchador.getApellido());

        }
    }
}
