package com.example.programador.pandevida;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.programador.pandevida.Adapters.VersiculoAdapter;
import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import com.example.programador.pandevida.basesdedatos.SQLiteManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Ratan on 7/29/2015.
 */
public class VersiculoFragment extends Fragment {

    // Atributos
    @BindView(R.id.recycler_versiculo)
    RecyclerView recycler_versiculo;

    VersiculoAdapter adapter;

    public static InterfazComunicacionTabs interfaz;
    //constructor
    public static VersiculoFragment NewInstance(InterfazComunicacionTabs inter) {
        VersiculoFragment fragment = new VersiculoFragment();
        interfaz = inter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se declara la una vista  para inflarse y se asocia con el layout=  "Capitulo_layout"
        View viewVersiculoFragment =inflater.inflate(R.layout.versiculo_layout,container,false);
        ButterKnife.bind(this, viewVersiculoFragment);

        //ToDo Mostrar los versiculos del capitulo en el que se está, sacado de las preferences

        adapter = new VersiculoAdapter(new ArrayList<String>(), interfaz);
        GridLayoutManager Layoutmanager = new GridLayoutManager(getContext(), 6);
        recycler_versiculo.setLayoutManager(Layoutmanager);
        recycler_versiculo.setHasFixedSize(true);
        recycler_versiculo.setAdapter(adapter);

        return viewVersiculoFragment;
    }

    public void mostrarVersiculo(int capitulo){

        SQLiteManager Bdd = SQLiteManager.getInstance();
        int cantidadVersiculos = Bdd.getVersiculos(getContext(),capitulo);

        int i =1;
        List<String> versiculos= new ArrayList<>();
        while(i<=cantidadVersiculos){
            versiculos.add(i+"");
            i++;
        }

        adapter = new VersiculoAdapter(versiculos, interfaz);
        recycler_versiculo.setAdapter(adapter);

    }

}
