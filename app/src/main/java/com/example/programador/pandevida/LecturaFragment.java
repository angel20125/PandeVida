package com.example.programador.pandevida;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.programador.pandevida.Adapters.LecturaAdapter;
import com.example.programador.pandevida.Interfaces.InterfazComunicacion;
import com.example.programador.pandevida.basesdedatos.MyDataBase;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.programador.pandevida.MainActivity.arrayLibroHuman;
import static com.example.programador.pandevida.MainActivity.biblia;
import static com.example.programador.pandevida.MainActivity.cantidadVersos;


public class LecturaFragment extends Fragment {
    //atributos
    @BindView(R.id.recycler_lectura) RecyclerView recycler_lectura;
    public  static InterfazComunicacion interfaz;

    //constructor
    public static LecturaFragment NewInstance(InterfazComunicacion inter) {
        LecturaFragment fragment = new LecturaFragment();
        interfaz = inter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewLecturaFragment = inflater.inflate(R.layout.lectura_layout, container,false);
        ButterKnife.bind(this, viewLecturaFragment);
        //para esconder la vista
        //viewLecturaFragment.setVisibility(View.INVISIBLE);
        //viewLecturaFragment.setVisibility(View.GONE);
        return viewLecturaFragment;
    }

    public void mostrarLectura(int versiculo){
        if(cantidadVersos.size()!=-1){
            cantidadVersos.clear();
        }
        LecturaAdapter adapter= new LecturaAdapter(cantidadVersos,interfaz);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        //Hago consulta a la BDD
        //Se pasa por parametro por aqui los libros de la biblia que se saca de la bases de datos
        //Lleno la lista y se la paso al adapter (El adapter recibe Interfaz!)
        //Le pongo layut manager y adapter al Recycler View
        Log.v("Manyulas","Biblia  Osis"+biblia.getOsis()+"  Biblia Capitula "+ biblia.getCapitulo());
        try {
            MyDataBase mdb = new MyDataBase(getContext().getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM 'bible_fts'"+
                    "WHERE book="+"'"+biblia.getOsis()+"'"+
                    "AND chapter="+"'"+biblia.getCapitulo()+"'",null);

            if(cursor.moveToFirst()){
                int numeroVerso=1;
                while(cursor.isAfterLast()!=true){
                    cantidadVersos.add((numeroVerso+"."+cursor.getString(3)));
                    cursor.moveToNext();
                    numeroVerso++;
                }
            }else{
                Log.v("LecturaFragment","error en VersiculoFragment: no se pudo leer el ultimo registro en");
            }
            db.close();
            cursor.close();
            mdb.close();
            mdb.finalize();
            Log.v("LecturaFragment","Libro:    "+ biblia.getLibro());
            Log.v("LecturaFragment","capitulo: "+ biblia.getCapitulo());
            Log.v("LecturaFragment","verso: "   +biblia.getVerso());
            Log.v("LecturaFragment", "---------------------------------");
            for (int i=0; i<=cantidadVersos.size()-1; i++){

                Log.v("LecturaFragment", " "+cantidadVersos.get(i));
            }
            Log.v("LecturaFragment", "---------------------------------");
            Log.v("LecturaFragment", " Tamaño: "+ cantidadVersos.size());
        }catch (Exception e){
            Log.v("LecturaFragment","Error en la conexcion de bases de datos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        recycler_lectura.setLayoutManager(layoutManager);
        recycler_lectura.scrollToPosition(versiculo-1);
        recycler_lectura.setHasFixedSize(true);
        recycler_lectura.setAdapter(adapter);
    }
}
