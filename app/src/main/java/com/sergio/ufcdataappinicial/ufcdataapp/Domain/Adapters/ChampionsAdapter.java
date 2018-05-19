package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.LuchadoresViewHolder> {

    private Context context;
    private Luchador[] luchadores;
    private OnItemClickListener listener;

    public ChampionsAdapter(Context context, Luchador[] luchadores, OnItemClickListener listener) {
        this.context = context;
        this.luchadores = luchadores;
        this.listener = listener;
    }

    @Override
    public LuchadoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ButterKnife.bind(parent);

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_champion, parent, false);

        return new LuchadoresViewHolder(context, item);
    }

    @Override
    public void onBindViewHolder(LuchadoresViewHolder viewHolder, int position) {
        Luchador luchador = luchadores[position];
        viewHolder.bindLuchador(luchador, listener);

    }

    // Número de elementos
    @Override
    public int getItemCount() {
        return luchadores.length;
    }


    public static class LuchadoresViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.txtName)
        TextView name;
        @BindView(R.id.txtNickName)
        TextView nickName;
        @BindView(R.id.txtWeight)
        TextView weightClass;
        @BindView(R.id.img)
        ImageView img;

        public LuchadoresViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bindLuchador(final Luchador luchador, final OnItemClickListener listener) {
            name.setText(String.format("%s %s", luchador.getNombre(), luchador.getApellido()));
            if (luchador.getNick() != null)
                nickName.setText(String.format("'%s'", luchador.getNick()));
            weightClass.setText(luchador.getCategoria());
            String imagen = luchador.getImgCinturon();
            if (imagen != null && !imagen.equals("")) {
                 Picasso.get().load(luchador.getImgCinturon()).into(img);
            } else {
                 Picasso.get().load(luchador.getImgPerfil()).into(img);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(luchador, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Luchador luchador, int position);
    }

}
