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


import com.example.programador.pandevida.Adapters.VersiculoAdapter;
import com.example.programador.pandevida.Interfaces.InterfazComunicacion;
import com.example.programador.pandevida.basesdedatos.MyDataBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import static com.example.programador.pandevida.MainActivity.biblia;
import static com.example.programador.pandevida.MainActivity.cantidadVersiculos;


/**
 * Created by Ratan on 7/29/2015.
 */
public class VersiculoFragment extends Fragment {

    // Atributos
    @BindView(R.id.recycler_versiculo) RecyclerView recycler_versiculo;
    public static InterfazComunicacion interfaz;
    //constructor
    public static VersiculoFragment NewInstance(InterfazComunicacion inter) {
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
        return viewVersiculoFragment;
    }

    public void mostrarVersiculo(int capitulo){
        if(cantidadVersiculos.size()!=-1){
            cantidadVersiculos.clear();
        }
        VersiculoAdapter adapter = new VersiculoAdapter(cantidadVersiculos, interfaz);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),6);
        //Hago consulta a la BDD
        //Se pasa por parametro por aqui los libros de la biblia que se saca de la bases de datos
        //Lleno la lista y se la paso al adapter (El adapter recibe Interfaz!)
        //Le pongo layut manager y adapter al Recycler View
        try {
         MyDataBase mdb = new MyDataBase(getContext().getApplicationContext(), 0);
         SQLiteDatabase db = mdb.getWritableDatabase();
         Cursor cursor;
         cursor = db.rawQuery("SELECT * FROM 'bible_fts'"+
                         "WHERE book="+"'"+biblia.getOsis()+"'"+
                         "AND chapter="+"'"+capitulo+"'",null);

         if(cursor.moveToFirst()){
             cursor.moveToLast();
             Log.v("VersiculoFragment", "El Ultimo verisculo es: "+cursor.getInt(2));
             biblia.setVersoFinal(cursor.getInt(2));
         }else{
                     Log.v("VersiculoFragment"," error en VersiculoFragment: no se pudo leer el ultimo registro en ");
         }
         db.close();
         cursor.close();
         mdb.close();
         mdb.finalize();
         }catch (Exception e){
             Log.v("VersiculoFragment","Error en la conexcion de bases de datos VersiculoFragment");
         } catch (Throwable throwable) {
             throwable.printStackTrace();
         }
        int indice = 1;
        String cadena = "";
        for (int i = 0; i <= biblia.getVersoFinal()- 1; i++) {
            cantidadVersiculos.add(i, cadena.valueOf(indice));
            indice++;
        }
        //  muestro lleno lista de capitulos
        Log.v("VersiculoFragment","-------------tamaño de lista-------------");
        Log.v("VersiculoFragment"," "+cantidadVersiculos.size());
        Log.v("VersiculoFragment ", biblia.getOsis()+" "+biblia.getCapitulo()+" "+ ((int)biblia.getVerso()));
        recycler_versiculo.setLayoutManager(layoutManager);
        recycler_versiculo.setHasFixedSize(true);
        recycler_versiculo.setAdapter(adapter);
    }

}
