package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private Context context;
    private Evento[] events;
    private OnItemClickListener listener;

    public EventsAdapter(Context context, Evento[] events, OnItemClickListener listener) {
        this.context = context;
        this.events = events;
        this.listener = listener;
    }

    // Asignamos la celda y la inflamos
    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ButterKnife.bind(parent);
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventsViewHolder(context, item);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder viewHolder, int position) {
        Evento event = events[position];
        viewHolder.bindEvento(event, listener);

    }

    // Número de elementos
    @Override
    public int getItemCount() {
        return events.length;
    }


    public static class EventsViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.txtFechaEvent)
        TextView fecha;
        @BindView(R.id.txtTituloEvent)
        TextView titulo;
        @BindView(R.id.txtSubtituloEvent)
        TextView subtitulo;
        @BindView(R.id.txtLocation)
        TextView location;
        @BindView(R.id.txtArena)
        TextView arena;
        @BindView(R.id.imgLuchador1)
        ImageView imgLuchador1;
        @BindView(R.id.imgLuchador2)
        ImageView imgLuchador2;

        public EventsViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bindEvento(final Evento event, final OnItemClickListener listener) {
            fecha.setText(event.getFecha());
            titulo.setText(event.getTitulo());
            subtitulo.setText(event.getSubtitulo());
            location.setText(event.getCiudad());
            arena.setText(event.getArena());
            if (event.getLuchador1() != null) {
                String imagen1 = event.getLuchador1().getImgPerfil();
                if (imagen1 != null && !imagen1.equals(""))
                     Picasso.get().load(event.getLuchador1().getImgCuerpoIzquierda()).placeholder(R.drawable.male_shadow_left).into(imgLuchador1);
                else
                    Picasso.get().load(R.drawable.male_shadow_left).into(imgLuchador1);

            }
            if(event.getLuchador2() != null){
                String imagen2 = event.getLuchador2().getImgPerfil();
                if (imagen2 != null && !imagen2.equals(""))
                     Picasso.get().load(event.getLuchador2().getImgCuerpoDerecha()).placeholder(R.drawable.male_shadow_right).into(imgLuchador2);
                else
                    Picasso.get().load(R.drawable.male_shadow_right).into(imgLuchador2);

            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(event, getAdapterPosition());
                }
            });
        }
    }

    public void setFilter(Evento[] events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Evento evento, int position);
    }

    /*public void updateData(Evento[] events){
        this.events = events;
        notifyDataSetChanged();
    }*/
}
