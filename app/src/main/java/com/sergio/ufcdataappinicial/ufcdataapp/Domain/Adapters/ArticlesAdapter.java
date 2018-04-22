package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.NoticiasViewHolder> {

    private Context context;
    private Noticia[] news;
    private OnItemClickListener listener;

    public ArticlesAdapter(Context context, Noticia[] news, OnItemClickListener listener) {
        this.context = context;
        this.news = news;
        this.listener = listener;
    }

    // Asignamos la celda y la inflamos
    @Override
    public NoticiasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // ButterKnife.bind(parent);
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        // view.setOnClickListener(this);
        return new NoticiasViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(NoticiasViewHolder viewHolder, int position) {
        Noticia noticia = news[position];
        viewHolder.bindNoticia(noticia, listener);
    }

    // Número de elementos
    @Override
    public int getItemCount() {
        return news.length;
    }


    public static class NoticiasViewHolder extends RecyclerView.ViewHolder {

        Context context;

        private TextView autor;
        private TextView fecha;
        private ImageView img;

        public NoticiasViewHolder(Context context, View itemView) {
            super(itemView);

            this.autor = itemView.findViewById(R.id.txtAutor);
            this.fecha = itemView.findViewById(R.id.txtFecha);
            this.img = itemView.findViewById(R.id.img);

            this.context = context;
        }

        public void bindNoticia(final Noticia noticia, final OnItemClickListener listener) {
            this.autor.setText(noticia.getAutor());
            this.fecha.setText(noticia.getFecha());
            String imagen = noticia.getImg();
            if (imagen != null && imagen != "") {
                Picasso.with(this.context).load(noticia.getImg()).into(this.img);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(noticia, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Noticia noticia, int position);
    }


    /*public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }*/
}
