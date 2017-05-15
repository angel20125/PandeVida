
package com.example.programador.pandevida;

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
import com.example.programador.pandevida.Utils.Constantes;
import com.example.programador.pandevida.basesdedatos.SQLiteManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.example.programador.pandevida.MainActivity.biblia;

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
        editor.putString("Osis", biblia.getOsis());
        editor.putString("Libro", biblia.getLibro());
        editor.putInt("Capitulo", biblia.getCapitulo());
        editor.putInt("Verso", biblia.getVerso());
        editor.apply();

        SharedPreferences prefs = getActivity().getSharedPreferences(Constantes.ULTIMA_LECTURA_PREFERENCE, MODE_PRIVATE);
        Log.d("Sandro", "Osis: "+prefs.getString("Osis", "Gen"));
        Log.d("Sandro", "Libro: "+prefs.getString("Libro", "Génesis"));
        Log.d("Sandro", "Capitulo: "+prefs.getInt("Capitulo", 1));
        Log.d("Sandro", "Verso: "+prefs.getInt("Verso", 1));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_lectura.setLayoutManager(layoutManager);
        recycler_lectura.scrollToPosition(biblia.getVerso()-1);// en esta seccion se le dice al scroll que  se coloque en el versiculo seleccionado
        recycler_lectura.setHasFixedSize(true);
        recycler_lectura.setAdapter(adapter);
        MainActivity.CambiarTitulo(biblia.getLibro());
        MainActivity.CambiarToolbar(true);

        return viewLecturaFragment;
    }

    public void mostrarLectura(int versiculo){

    }
}
