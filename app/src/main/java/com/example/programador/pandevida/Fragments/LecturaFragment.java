
package com.example.programador.pandevida.Fragments;

import android.content.SharedPreferences;
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
import com.example.programador.pandevida.Interfaces.InterfazComunicacionMainActivity;
import com.example.programador.pandevida.R;
import com.example.programador.pandevida.Utils.Constantes;
import com.example.programador.pandevida.basesdedatos.Biblia;
import com.example.programador.pandevida.basesdedatos.SQLiteManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
//import static com.example.programador.pandevida.Activities.MainActivity.biblia;
//import static com.example.programador.pandevida.Activities.MainActivity.Biblia;

/*Esta clase se encarga de consultar a la bases de datos para mostrar
la lectura del libro, capitulo y versiculo mostrado*/
public class LecturaFragment extends Fragment {
    //atributos
    @BindView(R.id.recycler_lectura) RecyclerView recycler_lectura;
    public  static InterfazComunicacionMainActivity MainActivity;
    //constructor
    public static LecturaFragment NewInstance(InterfazComunicacionMainActivity inter) {
        LecturaFragment fragment = new LecturaFragment();
        MainActivity = inter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewLecturaFragment = inflater.inflate(R.layout.lectura_layout, container,false);
        ButterKnife.bind(this, viewLecturaFragment);

        SQLiteManager Bdd = SQLiteManager.getInstance();

        LecturaAdapter adapter= new LecturaAdapter(Bdd.getLectura(getContext()));

        SharedPreferences.Editor editor = getActivity().getSharedPreferences(Constantes.ULTIMA_LECTURA_PREFERENCE, MODE_PRIVATE).edit();
        editor.putString("Osis", Biblia.getOsis());
        editor.putString("Libro", Biblia.getLibro());
        editor.putInt("Capitulo", Biblia.getCapitulo());
        editor.putInt("Verso", Biblia.getVerso());
        editor.apply();

        MainActivity.CambiarBotonNext(true);
        MainActivity.CambiarBotonBack(true);

        if(Biblia.getLibro().equals("Apocalipsis")&&Biblia.getCapitulo()==22){
            MainActivity.CambiarBotonNext(false);
        }

        if(Biblia.getLibro().equals("Génesis")&&Biblia.getCapitulo()==1){
            MainActivity.CambiarBotonBack(false);
        }


        SharedPreferences prefs = getActivity().getSharedPreferences(Constantes.ULTIMA_LECTURA_PREFERENCE, MODE_PRIVATE);
        Log.d("Sandro", "Preferences: Osis: "+prefs.getString("Osis", "Gen"));
        Log.d("Sandro", "Preferences: : "+prefs.getString("Libro", "Génesis"));
        Log.d("Sandro", "Preferences: Capitulo: "+prefs.getInt("Capitulo", 1));
        Log.d("Sandro", "Preferences: Verso: "+prefs.getInt("Verso", 1));

        Log.d("Sandro", "Static: Osis: "+Biblia.getOsis());
        Log.d("Sandro", "Static: Libro: "+Biblia.getLibro());
        Log.d("Sandro", "Static: Capitulo: "+Biblia.getCapitulo());
        Log.d("Sandro", "Static: Verso: "+Biblia.getVerso());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_lectura.setLayoutManager(layoutManager);
        recycler_lectura.scrollToPosition(Biblia.getVerso()-1);// en esta seccion se le dice al scroll que  se coloque en el versiculo seleccionado
        recycler_lectura.setHasFixedSize(true);
        recycler_lectura.setAdapter(adapter);
        MainActivity.CambiarTitulo(Biblia.getLibro());
        MainActivity.CambiarToolbar(true);

        return viewLecturaFragment;
    }

    public void mostrarLectura(int versiculo){

    }
}
