
package com.example.programador.pandevida;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.programador.pandevida.Adapters.LecturaAdapter;
import com.example.programador.pandevida.basesdedatos.SQLiteManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.programador.pandevida.MainActivity.biblia;

/*Esta clase se encarga de consultar a la bases de datos para mostrar
la lectura del libro, capitulo y versiculo mostrado*/
public class LecturaFragment extends Fragment {
    //atributos
    @BindView(R.id.recycler_lectura) RecyclerView recycler_lectura;
    //public  static InterfazComunicacionTabs interfaz;
    //constructor
    public static LecturaFragment NewInstance(/*InterfazComunicacionTabs inter*/) {
        LecturaFragment fragment = new LecturaFragment();
        //interfaz = inter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewLecturaFragment = inflater.inflate(R.layout.lectura_layout, container,false);
        ButterKnife.bind(this, viewLecturaFragment);

        SQLiteManager Bdd = SQLiteManager.getInstance();

        LecturaAdapter adapter= new LecturaAdapter(Bdd.getLectura(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_lectura.setLayoutManager(layoutManager);
        recycler_lectura.scrollToPosition(biblia.getVerso());// en esta seccion se le dice al scroll que  se coloque en el versiculo seleccionado
        recycler_lectura.setHasFixedSize(true);
        recycler_lectura.setAdapter(adapter);
        return viewLecturaFragment;
    }

    public void mostrarLectura(int versiculo){

    }
}
