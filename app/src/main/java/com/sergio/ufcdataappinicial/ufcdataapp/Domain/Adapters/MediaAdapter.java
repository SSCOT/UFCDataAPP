package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private Context context;
    private Media[] mediaItems;
    private OnItemClickListener listener;

    public MediaAdapter(Context context, Media[] mediaItems, OnItemClickListener listener) {
        this.context = context;
        this.mediaItems = mediaItems;
        this.listener = listener;
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, parent, false);
        return new MediaViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder viewHolder, int position) {
        Media mediaItem = mediaItems[position];
        viewHolder.bindMedia(mediaItem, listener);
    }

    @Override
    public int getItemCount() {
        return mediaItems.length;
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.txtTituloMedia)
        TextView titulo;
        @BindView(R.id.txtDescripcionMedia)
        TextView descripcion;
        @BindView(R.id.txtFechaMedia)
        TextView fecha;
        @BindView(R.id.iconMediaType)
        ImageView iconMediaType;
        @BindView(R.id.imgMedia)
        ImageView img;


        public MediaViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.context = context;
        }

        public void bindMedia(final Media mediaItem, final OnItemClickListener listener) {
            titulo.setText(mediaItem.getTitulo());
            descripcion.setText(mediaItem.getDescripcion());
            fecha.setText(mediaItem.getFecha());

            String imagen = mediaItem.getImg();

            if (imagen != null && !imagen.equals("")) {
                 Picasso.get().load(mediaItem.getImg()).into(img);
            } else {
                img.setImageResource(R.drawable.ufc_logo_white);
            }

            switch (mediaItem.getTipo()) {
                case "PHOTOGALLERY":
                    iconMediaType.setImageResource(R.drawable.ic_search);
                    break;
                case "INTERNALVIDEO":
                    iconMediaType.setImageResource(R.drawable.ic_play_circle_white);
                    break;
                case "EMBEDDEDVIDEO":
                    iconMediaType.setImageResource(R.drawable.ic_play_circle_white);
                    break;
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(mediaItem, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Media mediaItem, int position);
    }

}
