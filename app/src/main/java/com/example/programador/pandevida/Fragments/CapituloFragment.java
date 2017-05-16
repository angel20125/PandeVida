package com.example.programador.pandevida.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.programador.pandevida.Adapters.CapituloAdapter;
import com.example.programador.pandevida.Interfaces.InterfazComunicacionTabs;
import com.example.programador.pandevida.R;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
* Clase CapituloFragment encargada pasar  por parameto  la InterfazComunicacionTabs
* */
public class CapituloFragment extends Fragment {

    CapituloAdapter adapter;

    private int numeroCapitulos;
    //Atributos
    @BindView(R.id.recycler_capitulo) RecyclerView recycler_capitulo;
    private static InterfazComunicacionTabs interfaz;

    //constructor
    public static CapituloFragment NewInstance(InterfazComunicacionTabs inter) {
        CapituloFragment fragment = new CapituloFragment();
        interfaz = inter;
        return fragment;
    }

    @Override
    public void onCreate (Bundle bundle){
        super.onCreate(bundle);
        //ToDo Llamar de las preferences el Libro donde se esta.
        /*
        if (bundle == null){
            //cantidadCapitulos.clear();
        }else{
            cantidadCapitulos= bundle.getStringArrayList("cantidadCapitulos");
            for (int i = 0; i <= biblia.getCapituloFinal() - 1; i++) {
                Log.v("CapitulosBundle", " " + cantidadCapitulos.get(i));
            }
            //  muestro lleno lista de capitulos
            Log.v ("CapitulosBundle","-------------tamaño de lista-------------");
            Log.v("CapitulosBundle"," "+cantidadCapitulos.size());

        }*/
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            //Restore the fragment's state here

        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putStringArrayList("cantidadCapitulos", (ArrayList<String>) cantidadCapitulos);

        //Save the fragment's state here
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se declara la una vista  para inflarse y se asocia con el layout=  "Capitulo_layout"
        View viewCapituloFragment= inflater.inflate(R.layout.capitulo_layout,container,false);
        ButterKnife.bind(this,viewCapituloFragment);// no se para que sirve;

        /*if (savedInstanceState== null){//ToDo Mostrar el capitulo en el que se está, sacado de las preferences
            //como el fragmento se creo la primera vez no hacer nada
        }else{*/

            //adapter = new CapituloAdapter(new ArrayList<String>(), interfaz);
            adapter = null;
            GridLayoutManager Layoutmanager = new GridLayoutManager(getContext(), 6);
            recycler_capitulo.setLayoutManager(Layoutmanager);
            recycler_capitulo.setHasFixedSize(true);
            recycler_capitulo.setAdapter(adapter);
            return viewCapituloFragment;
        /*}
        return  viewCapituloFragment;*/
    }

    public void mostrarCapitulo(int cantidadCapitulos) {

        int i =1;
        List<String> capitulos= new ArrayList<>();
        while(i<=cantidadCapitulos){
            capitulos.add(""+(i));
            i++;
        }

        Log.d("Sandro", "mostrarCapitulo: ");
        adapter = new CapituloAdapter(capitulos, interfaz);
        recycler_capitulo.setAdapter(adapter);

    }

}
//consulta correcta
/*
* cursor = db.rawQuery("SELECT book, chapter, verse, content FROM 'bible_fts'"+
                    "WHERE book="+"'"+biblia.getLibro()+"'"+
                    "AND chapter="+"'"+biblia.getCapitulo()+"'",null);
;*/