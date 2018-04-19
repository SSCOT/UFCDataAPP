package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.ButterKnife;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.LuchadoresViewHolder> {

    private Context context;
    private Luchador[] luchadores;

    public ChampionsAdapter(Context context, Luchador[] luchadores) {
        this.context = context;
        this.luchadores = luchadores;
    }

    // Asignamos la celda y la inflamos
    @Override
    public LuchadoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ButterKnife.bind(parent);

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_champion, parent, false);

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

        Context context;

        private TextView name;
        private TextView nickName;
        private TextView weightClass;
        private ImageView img;

        public LuchadoresViewHolder(Context context, View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtName);
            nickName = itemView.findViewById(R.id.txtNickName);
            weightClass = itemView.findViewById(R.id.txtWeight);
            img = itemView.findViewById(R.id.img);

            this.context = context;
        }

        public void bindLuchador(Luchador luchador) {
            name.setText(luchador.getNombre() + " " +luchador.getApellido());
            nickName.setText(luchador.getNick());
            weightClass.setText(luchador.getCategoria());
            String imagen = luchador.getImgCinturon();
            if (imagen != null && imagen != "") {
                Picasso.with(context).load(luchador.getImgCinturon()).into(img);
            } else {
                Picasso.with(context).load(luchador.getImgPerfil()).into(img);
            }
        }
    }
}
