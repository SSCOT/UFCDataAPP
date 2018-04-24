package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Luchador;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private Context context;
    private Evento[] events;

    public EventsAdapter(Context context, Evento[] events) {
        this.context = context;
        this.events = events;
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
        viewHolder.bindEvento(event);

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

        public EventsViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bindEvento(Evento event) {
            fecha.setText(event.getFecha());
            titulo.setText(event.getTitulo());
            subtitulo.setText(event.getSubtitulo());
            location.setText(event.getCiudad());
            arena.setText(event.getArena());
        }
    }
}
