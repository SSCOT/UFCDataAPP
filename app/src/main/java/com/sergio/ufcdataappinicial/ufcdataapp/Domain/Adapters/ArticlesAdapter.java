package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new NoticiasViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(NoticiasViewHolder viewHolder, int position) {
        Noticia noticia = news[position];
        viewHolder.bindNoticia(noticia, listener);
    }

    @Override
    public int getItemCount() {
        return news.length;
    }


    public static class NoticiasViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.txtTitle)
        TextView titulo;
        @BindView(R.id.txtAutor)
        TextView autor;
        @BindView(R.id.txtFecha)
        TextView fecha;
        @BindView(R.id.img)
        ImageView img;

        public NoticiasViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bindNoticia(final Noticia noticia, final OnItemClickListener listener) {
            titulo.setText(noticia.getTitulo());
            autor.setText(noticia.getAutor());
            fecha.setText(noticia.getFecha());
            String imagen = noticia.getImg();
            if (imagen != null && !imagen.equals(""))
                Picasso.get().load(noticia.getImg()).placeholder(R.drawable.ufc_logo_white).into(img);
            else
                img.setImageResource(R.drawable.ufc_logo_white);


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
}
