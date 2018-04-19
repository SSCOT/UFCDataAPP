package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Noticia;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.NoticiasViewHolder> {

    private Context context;
    private Noticia[] news;

    public ArticlesAdapter(Context context, Noticia[] news) {
        this.context = context;
        this.news = news;
    }

    // Asignamos la celda y la inflamos
    @Override
    public NoticiasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ButterKnife.bind(parent);

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);

        return new NoticiasViewHolder(context, item);
    }

    @Override
    public void onBindViewHolder(NoticiasViewHolder viewHolder, int position) {
        Noticia noticia = news[position];
        viewHolder.bindLuchador(noticia);

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

            autor = itemView.findViewById(R.id.txtAutor);
            fecha = itemView.findViewById(R.id.txtFecha);
            img = itemView.findViewById(R.id.img);

            this.context = context;
        }

        public void bindLuchador(Noticia noticia) {
            autor.setText(noticia.getAutor());
            fecha.setText(noticia.getFecha());
            if (noticia.getImg() != null && noticia.getImg() != "") {
                Picasso.with(context).load(noticia.getImg()).into(img);
            }
        }
    }
}
