package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LuchadoresAdapter extends RecyclerView.Adapter<LuchadoresAdapter.LuchadoresViewHolder> {

    private Context context;
    private Luchador[] luchadores;
    private OnItemClickListener listener;

    public LuchadoresAdapter(Context context, Luchador[] luchadores, OnItemClickListener listener) {
        this.context = context;
        this.luchadores = luchadores;
        this.listener = listener;
    }

    // Asignamos la celda y la inflamos
    @Override
    public LuchadoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luchador, parent, false);
        return new LuchadoresViewHolder(context, item);
    }

    @Override
    public void onBindViewHolder(LuchadoresViewHolder viewHolder, int position) {
        Luchador luchador = luchadores[position];
        viewHolder.bindLuchador(luchador, listener);

    }

    @Override
    public int getItemCount() {
        return luchadores.length;
    }

    public static class LuchadoresViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.txtLuchador1)
        TextView txtName;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.txtNick)
        TextView txtNick;

        public LuchadoresViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bindLuchador(final Luchador luchador, final OnItemClickListener listener) {
            txtName.setText(String.format("%s %s", luchador.getNombre(), luchador.getApellido()));
            if (luchador.getNick() != null) {
                txtNick.setText(String.format("'%s'", luchador.getNick()));
            } else {
                txtNick.setText("");
            }
            String imagen = luchador.getImgPerfil();
            if (imagen != null && !imagen.equals("")) {
                Picasso.with(context).load(luchador.getImgPerfil()).into(img);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(luchador, getAdapterPosition());
                }
            });
        }
    }

    public void setFilter(Luchador[] luchadores) {
        this.luchadores = luchadores;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Luchador luchador, int position);
    }
}
