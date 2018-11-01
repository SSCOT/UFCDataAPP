package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Combate;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FightsEventosAdapter extends RecyclerView.Adapter<FightsEventosAdapter.FightsEventsViewHolder> {

    private Context context;
    private Combate[] fights;
    private OnItemClickListener listener;

    public FightsEventosAdapter(Context context, Combate[] fights, OnItemClickListener listener) {
        this.context = context;
        this.fights = fights;
        this.listener = listener;
    }

    // Asignamos la celda y la inflamos
    @Override
    public FightsEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fight_evento, parent, false);
        return new FightsEventsViewHolder(context, item);
    }

    @Override
    public void onBindViewHolder(FightsEventsViewHolder viewHolder, int position) {
        Combate fight = fights[position];
        viewHolder.bindFight(fight, listener);

    }

    @Override
    public int getItemCount() {
        return fights.length;
    }

    public static class FightsEventsViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.titulo)
        TextView txtTitulo;

        @BindView(R.id.imgLuchador1)
        ImageView imgLuchador1;
        @BindView(R.id.txtLuchador1)
        TextView txtLuchador1;
        @BindView(R.id.txtLuchadorApe1)
        TextView txtLuchadorApe1;

        @BindView(R.id.imgLuchador2)
        ImageView imgLuchador2;
        @BindView(R.id.txtLuchador2)
        TextView txtLuchador2;
        @BindView(R.id.txtLuchadorApe2)
        TextView txtLuchadorApe2;

        @BindView(R.id.txtTipoFinal)
        TextView txtTipoFinal;

        @BindView(R.id.win1)
        TextView win1;
        @BindView(R.id.win2)
        TextView win2;


        public FightsEventsViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bindFight(final Combate fight, final OnItemClickListener listener) {

            // Si no tenemos los datos de los peleadores indicamos "pelea por confirmar"
            if((fight.getNombre1() != null && !fight.getNombre1().equals("")) || ( fight.getNombre2() != null && fight.getNombre2() != "")){
                if(fight.getDescripcion() != null)
                    txtTitulo.setText(fight.getDescripcion());
                else
                    txtTitulo.setVisibility(View.GONE);

                String img1 = fight.getImgPerfil1();
                if (img1 != null && !img1.equals(""))
                     Picasso.get().load(img1).placeholder(R.drawable.male_profile_shadow_white).into(imgLuchador1);
                else
                    Picasso.get().load(R.drawable.male_profile_shadow_white).into(imgLuchador1);

                String img2 = fight.getImgPerfil2();
                if (img2 != null && !img2.equals(""))
                     Picasso.get().load(img2).placeholder(R.drawable.male_profile_shadow_white).into(imgLuchador2);
                else
                    Picasso.get().load(R.drawable.male_profile_shadow_white).into(imgLuchador2);

                txtLuchador1.setText(fight.getNombre1());
                txtLuchadorApe1.setText(fight.getApellido1());
                txtLuchador2.setText(fight.getNombre2());
                txtLuchadorApe2.setText(fight.getApellido2());
                if(fight.getResult() != null){
                    if(fight.getResult().getMetodoFinalizacion() != null){
                        txtTipoFinal.setVisibility(View.VISIBLE);
                        txtTipoFinal.setText(fight.getResult().getMetodoFinalizacion());
                    }
                }
                if(fight.getGanador1() != null && fight.getGanador1())
                    win1.setVisibility(View.VISIBLE);
                else if (fight.getGanador2() != null && fight.getGanador2())
                    win2.setVisibility(View.VISIBLE);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(fight, getAdapterPosition());
                    }
                });
            } else {
                txtTitulo.setText("fight to be confirmed");
                txtTitulo.setBackgroundColor(context.getResources().getColor(R.color.secondary_text));
                txtTitulo.setTextColor(context.getResources().getColor(R.color.white));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Combate fight, int position);
    }

}
