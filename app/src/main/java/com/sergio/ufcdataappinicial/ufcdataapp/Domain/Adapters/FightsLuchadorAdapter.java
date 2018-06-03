package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.LuchadorCombate;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FightsLuchadorAdapter extends RecyclerView.Adapter<FightsLuchadorAdapter.LuchadoresViewHolder> {

    private Context context;
    private LuchadorCombate[] fights;

    public FightsLuchadorAdapter(Context context, LuchadorCombate[] fights) {
        this.context = context;
        this.fights = fights;
    }

    // Asignamos la celda y la inflamos
    @Override
    public LuchadoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fight_luchador, parent, false);
        return new LuchadoresViewHolder(context, item);
    }

    @Override
    public void onBindViewHolder(LuchadoresViewHolder viewHolder, int position) {
        LuchadorCombate fight = fights[position];
        viewHolder.bindFight(fight);

    }

    @Override
    public int getItemCount() {
        return fights.length;
    }

    public static class LuchadoresViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.titulo)
        TextView txtTitulo;
        @BindView(R.id.imgLuchador)
        ImageView imgLuchador;
        @BindView(R.id.txtLuchador)
        TextView txtLuchador;
        @BindView(R.id.txtLuchadorApe)
        TextView txtLuchadorApe;
        @BindView(R.id.txtTipoFinal)
        TextView txtTipoFinal;
        @BindView(R.id.result)
        TextView txtResult;
        @BindView(R.id.txtFecha)
        TextView txtFecha;

        public LuchadoresViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bindFight(final LuchadorCombate fight) {
            if (fight.getEvento().getNombre() != null)
                txtTitulo.setText(fight.getEvento().getNombre());
            if (fight.getEvento().getFecha() != null)
                txtFecha.setText(fight.getEvento().getFecha());
            if (fight.getOponente().getNombre() != null)
                txtLuchador.setText(fight.getOponente().getNombre());
            if (fight.getOponente().getApellido() != null)
                txtLuchadorApe.setText(fight.getOponente().getApellido());

            String imagen = fight.getOponente().getImg();
            if (imagen != null && !imagen.equals(""))
                 Picasso.get().load(imagen).placeholder(R.drawable.male_profile_shadow).into(imgLuchador);
            else
                Picasso.get().load(R.drawable.male_profile_shadow).into(imgLuchador);

            String result = fight.getResultado().getResultado();
            if (result != null) {
                switch (result) {
                    case "Win":
                        txtResult.setText("W");
                        txtResult.setBackgroundColor(context.getResources().getColor(R.color.win));
                        break;
                    case "Loss":
                        txtResult.setText("L");
                        txtResult.setBackgroundColor(context.getResources().getColor(R.color.black));
                        break;
                    case "Draw":
                        txtResult.setText("D");
                        txtResult.setBackgroundColor(context.getResources().getColor(R.color.secondary_text));
                        break;
                    default:
                        txtResult.setText("-");
                        txtResult.setTextColor(context.getResources().getColor(R.color.black));
                        txtResult.setBackgroundColor(context.getResources().getColor(R.color.white));
                        break;
                }
            } else {
                txtResult.setText("-");
                txtResult.setTextColor(context.getResources().getColor(R.color.black));
                txtResult.setBackgroundColor(context.getResources().getColor(R.color.white));
            }

            if (fight.getEvento().getFecha() != null)
                txtFecha.setText(fight.getEvento().getFecha());

            if (fight.getResultado().getTipoFinalizacion() != null)
                txtTipoFinal.setText(fight.getResultado().getTipoFinalizacion());

        }
    }


}
