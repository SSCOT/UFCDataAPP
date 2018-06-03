package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Foto;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MediaViewHolder> {

    private Context context;
    private Foto[] fotos;
    private OnItemClickListener listener;

    public PictureAdapter(Context context, Foto[] fotos, OnItemClickListener listener) {
        this.context = context;
        this.fotos = fotos;
        this.listener = listener;
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new MediaViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder viewHolder, int position) {
        Foto foto = fotos[position];
        viewHolder.bindMedia(foto, listener);
    }

    @Override
    public int getItemCount() {
        return fotos.length;
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.imgGallery)
        ImageView img;


        public MediaViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.context = context;
        }

        public void bindMedia(final Foto foto, final OnItemClickListener listener) {
            String imagen = foto.getThumbnail();
            if (imagen != null && !imagen.equals("")) {
                 Picasso.get().load(imagen).placeholder(R.drawable.ufc_logo_white).into(img);
            } else {
                Picasso.get().load(R.drawable.ufc_logo_white).into(img);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(foto, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Foto foto, int position);
    }

}
