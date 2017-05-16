package com.example.programador.pandevida.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.programador.pandevida.Adapters.LibroAdapter;
import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import com.example.programador.pandevida.R;
import com.example.programador.pandevida.basesdedatos.SQLiteManager;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Ratan on 7/29/2015.
 */
public class LibroFragment extends Fragment {
    //Atributos
    @BindView(R.id.Recycler_Libro) RecyclerView Recycler_Libro;
    private static InterfazComunicacionTabs interfaz;

    //Constructor
    public static LibroFragment NewInstance(InterfazComunicacionTabs inter){

        LibroFragment fragment = new LibroFragment();
        interfaz = inter;
        return fragment;
    }

    @Override
    public void onCreate (Bundle bundle){

        super.onCreate(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        //Se declara la una vista  para inflarse y se asocia con el layout=  "libro_layout"
        View viewLibroFragment= inflater.inflate(R.layout.libro_layout,container,false);
        ButterKnife.bind(this,viewLibroFragment);

        //Consulta a la BDD y se carga la info al adapter

        SQLiteManager Bdd = SQLiteManager.getInstance();

        //  Se pasa por parametro por aqui los libros de la biblia que se saca de la bases de datos
        //LibroAdapter adapter = new LibroAdapter(Arrays.asList(biblia.getLibroHumanAntiguoTest()), interfaz);
        //Se pasa por parametro por aqui los libros de la biblia que se saca de la bases de datos
        LibroAdapter adapter = new LibroAdapter(Bdd.getLibros(getContext()), interfaz);
        GridLayoutManager Layoutmanager = new GridLayoutManager(getContext(), 2);
        Recycler_Libro.setLayoutManager(Layoutmanager);
        Recycler_Libro.setHasFixedSize(true);
        Recycler_Libro.setAdapter(adapter);

        return viewLibroFragment;
    }
}

