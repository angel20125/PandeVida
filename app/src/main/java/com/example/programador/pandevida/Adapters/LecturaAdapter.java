package com.example.programador.pandevida.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.programador.pandevida.R;

import java.util.List;



/**
 * Created by angel20125 on 17/04/17.
 */

public class LecturaAdapter extends RecyclerView.Adapter<LecturaAdapter.LecturaViewHolder> {
    //atibutuos
    List<String> listVersos;
    //constructor
    public LecturaAdapter(List<String> listVersos){
        this.listVersos=listVersos;

    }


    public class LecturaViewHolder extends RecyclerView.ViewHolder {
        //Atributos
        TextView versoTV;
        FrameLayout fondo;

        public LecturaViewHolder(View itemView) {
            super(itemView);
            //enlace logico con los componetes que estan dentro de lectura_item
            versoTV= (TextView) itemView.findViewById(R.id.versoTV);
            fondo= (FrameLayout) itemView.findViewById(R.id.layoutLectura);
        }
    }

    @Override
    public LecturaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lectura_item, parent, false);
        final LecturaViewHolder holder = new LecturaViewHolder(v);

        /*holder.fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(interfaz!=null){
                   int numeroVersosClick= holder.getAdapterPosition()+1;
                   Log.v("Angel"," LecturaAdapter  NO NULL "+((int)holder.getAdapterPosition()+1));

                   //Toast toast= Toast.makeText(contextMain,""+holder.getAdapterPosition()+1,Toast.LENGTH_LONG);
                   //toast.show();
               }else{
                   Log.v("Angel"," LecturaAdapter  SI NULL");
               }
            }
        });*/
        return holder;
    }

    @Override
    public void onBindViewHolder(LecturaAdapter.LecturaViewHolder holder, int position) {
        holder.versoTV.setText(listVersos.get(position));
    }

    @Override
    public int getItemCount() {
        return listVersos.size();
    }


}
