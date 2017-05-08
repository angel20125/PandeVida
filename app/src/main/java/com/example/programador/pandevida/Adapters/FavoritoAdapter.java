package com.example.programador.pandevida.Adapters;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by angel20125 on 24/4/17.
 */

public class FavoritoAdapter extends  RecyclerView.Adapter<FavoritoAdapter.FavoritoViewHolder>{

     String libro;
     String capitulo;
     String verso;
     String contenido;
     //constructor
     public FavoritoAdapter(String plibro, String pcapitulo, String pverso, String pcontenido){
         libro=plibro; capitulo=pcapitulo; verso=pverso; contenido=pcontenido;
     }


public class FavoritoViewHolder extends RecyclerView.ViewHolder{
        public FavoritoViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public FavoritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;//retornar la vista
    }

    @Override
    public void onBindViewHolder(FavoritoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;// retornar el tamaño de la lista
    }
}
