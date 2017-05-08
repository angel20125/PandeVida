package com.example.programador.pandevida;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.programador.pandevida.Interfaces.InterfazComunicacion;
import com.example.programador.pandevida.basesdedatos.MyDataBase;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.x;
import static com.example.programador.pandevida.MainActivity.arrayLibroHuman;

import static com.example.programador.pandevida.MainActivity.biblia;
import static com.example.programador.pandevida.MainActivity.cantidadCapitulos;

/*
* Clase CapituloFragment encargada pasar  por parameto  la InterfazComunicacion
* */
public class CapituloFragment extends Fragment {

    private int numeroCapitulos;
    //Atributos
    @BindView(R.id.recycler_capitulo) RecyclerView recycler_capitulo;
    private static InterfazComunicacion interfaz;

    //constructor
    public static CapituloFragment NewInstance(InterfazComunicacion inter) {
        CapituloFragment fragment = new CapituloFragment();
        interfaz = inter;
        return fragment;
    }

    @Override
    public void onCreate (Bundle bundle){
        super.onCreate(bundle);
        if (bundle == null){
            cantidadCapitulos.clear();
        }else{
            cantidadCapitulos= bundle.getStringArrayList("cantidadCapitulos");
            for (int i = 0; i <= biblia.getCapituloFinal() - 1; i++) {
                Log.v("CapitulosBundle", " " + cantidadCapitulos.get(i));
            }
            //  muestro lleno lista de capitulos
            Log.v ("CapitulosBundle","-------------tamaño de lista-------------");
            Log.v("CapitulosBundle"," "+cantidadCapitulos.size());

        }
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
        outState.putStringArrayList("cantidadCapitulos", (ArrayList<String>) cantidadCapitulos);

        //Save the fragment's state here
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Se declara la una vista  para inflarse y se asocia con el layout=  "Capitulo_layout"
        View viewCapituloFragment= inflater.inflate(R.layout.capitulo_layout,container,false);
        ButterKnife.bind(this,viewCapituloFragment);// no se para que sirve;
        if (savedInstanceState== null){
            //como el fragmento se creo la primera vez no hacer nada
        }else{

            CapituloAdapter adapter = new CapituloAdapter(cantidadCapitulos, interfaz);
            GridLayoutManager Layoutmanager = new GridLayoutManager(getContext(), 6);
            recycler_capitulo.setLayoutManager(Layoutmanager);
            recycler_capitulo.setHasFixedSize(true);
            recycler_capitulo.setAdapter(adapter);
            viewCapituloFragment= inflater.inflate(R.layout.capitulo_layout,container,false);
            return viewCapituloFragment;
        }
        return  viewCapituloFragment;
    }

    public void mostrarCapitulo(String libro) {
       /*
        if (cantidadCapitulos.size() !=0) {
            cantidadCapitulos.clear();
        }
        */
        CapituloAdapter adapter = new CapituloAdapter(cantidadCapitulos, interfaz);
        GridLayoutManager Layoutmanager = new GridLayoutManager(getContext(), 6);
        //Hago consulta a la BDD
        //Se pasa por parametro por aqui los libros de la biblia que se saca de la bases de datos
        //Lleno la lista y se la paso al adapter (El adapter recibe Interfaz!)
        //Le pongo layut manager y adapter al Recycler View
        //Consulta de la bases de datos, para obtener el ultimo capitulo
        try {
            MyDataBase mdb = new MyDataBase(getContext().getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM 'books_fts'"+
                    "WHERE human="+"'"+libro+"'",null);
            if(cursor.moveToFirst()){
                Log.v("CapituloFragment", "SI TIENE REGISTRO  "+cursor.getString(1));
                biblia.setCapituloFinal(cursor.getInt(3));
                biblia.setOsis(cursor.getString(1));
            }else{
                Log.v("CapituloFragment","NO TIENE REGISTRO");
            }
            cursor.close();
            cursor.close();
            mdb.close();
            mdb.finalize();
        }catch (Exception e){
            Log.v("CapituloFragment","Error en la bases de datos en Capitulos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        int indice = 1;
        String cadena = "";
        for (int i = 0; i <= biblia.getCapituloFinal() - 1; i++) {
            cantidadCapitulos.add(i, cadena.valueOf(indice));
            indice++;
        }
        //Muestro la cantidad de versiculos por el Log
        for (int i = 0; i <= biblia.getCapituloFinal() - 1; i++) {
            Log.v("Capitulos", " " + cantidadCapitulos.get(i));
        }
        //  muestro lleno lista de capitulos
        Log.v ("Capitulos","-------------tamaño de lista-------------");
        Log.v("Capitulos"," "+cantidadCapitulos.size());

        recycler_capitulo.setLayoutManager(Layoutmanager);
        recycler_capitulo.setHasFixedSize(true);
        recycler_capitulo.setAdapter(adapter);
    }

}
//consulta correcta
/*
* cursor = db.rawQuery("SELECT book, chapter, verse, content FROM 'bible_fts'"+
                    "WHERE book="+"'"+biblia.getLibro()+"'"+
                    "AND chapter="+"'"+biblia.getCapitulo()+"'",null);
;*/