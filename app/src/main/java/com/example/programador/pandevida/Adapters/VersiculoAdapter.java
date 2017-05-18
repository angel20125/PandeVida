package com.example.programador.pandevida.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import com.example.programador.pandevida.R;
import com.example.programador.pandevida.basesdedatos.Biblia;

import java.util.List;


//import static com.example.programador.pandevida.Activities.MainActivity.biblia;


/**
 * Created by angel20125 on 17/04/17.
 */

public class VersiculoAdapter extends RecyclerView.Adapter<VersiculoAdapter.VersiculoViewHolder> {

    //atributos
    List<String> versiculos;
    InterfazComunicacionTabs interfaz;
    public VersiculoAdapter(List<String> versiculos, InterfazComunicacionTabs interfaz){
        this.versiculos =versiculos;
        this.interfaz=interfaz;
    }


    public class VersiculoViewHolder extends RecyclerView.ViewHolder {
        //Atributos
        TextView numero_versiculo;
        FrameLayout fondo;
        //constructor
        public VersiculoViewHolder(View itemView) {
            super(itemView);
            //enlace logico con los componetes que estan dentro de versiculo_item
            numero_versiculo= (TextView) itemView.findViewById(R.id.versiculoTV);
            fondo= (FrameLayout) itemView.findViewById(R.id.layoutVersiculo);
        }

    }

    @Override
    public VersiculoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.versiculo_item, viewGroup, false);
        final VersiculoViewHolder holder = new VersiculoViewHolder(v);



        return holder;
    }

    @Override
    public void onBindViewHolder(VersiculoViewHolder holder, final int position) {
        holder.numero_versiculo.setText(versiculos.get(position));

        holder.fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(interfaz!=null){
                    Biblia.setVerso((position+1));
                    Log.v("Angel","VersiculoAdapter Numero de versiculo es: "+position+1);
                    Log.v("Angel","VersiculoAdapter Numero de versiculo es: "+Biblia.getVerso());



                    interfaz.IrALectura();//donde se envia la informacion atraves de interfaz
                }else{
                    Log.v("Angel","VersiculoAdapter Interfaz nula!");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return versiculos.size();

    }


}
