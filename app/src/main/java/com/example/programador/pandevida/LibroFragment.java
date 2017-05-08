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

import com.example.programador.pandevida.Adapters.LibroAdapter;
import com.example.programador.pandevida.Interfaces.InterfazComunicacion;
import com.example.programador.pandevida.basesdedatos.MyDataBase;

import java.util.Arrays;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.programador.pandevida.MainActivity.arrayLibroHuman;


//import static com.example.programador.pandevida.R.id.gridViewLibro;
//import static com.example.programador.pandevida.TabFragment.getViewPager;
/**
 * Created by Ratan on 7/29/2015.
 */
public class LibroFragment extends Fragment {
    //Atributos
    @BindView(R.id.Recycler_Libro) RecyclerView Recycler_Libro;
    private static InterfazComunicacion interfaz;

    //Constructor
    public static LibroFragment NewInstance(InterfazComunicacion inter){

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
        try {
            MyDataBase mdb = new MyDataBase(getContext().getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT number, osis, human, chapters FROM 'books_fts'", null);
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                int i =-1;
                while (cursor.isAfterLast() == false) {
                    i++;
                        //arrayLibroOsis  [i]=cursor.getString(1);
                        arrayLibroHuman [i]=cursor.getString(2);
                        //arrayCapituloFinal[i]= cursor.getInt(3);
                        cursor.moveToNext();
                }
            }

         db.close();
         cursor.close();
         mdb.close();
         mdb.finalize();
        }catch(Exception e){
                Log.v("Angel", "error en la conexion de bases de datos en libro Fragment");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //  Se pasa por parametro por aqui los libros de la biblia que se saca de la bases de datos
        //LibroAdapter adapter = new LibroAdapter(Arrays.asList(biblia.getLibroHumanAntiguoTest()), interfaz);
        //Se pasa por parametro por aqui los libros de la biblia que se saca de la bases de datos
        LibroAdapter adapter = new LibroAdapter(Arrays.asList(arrayLibroHuman), interfaz);
        GridLayoutManager Layoutmanager = new GridLayoutManager(getContext(), 2);
        Recycler_Libro.setLayoutManager(Layoutmanager);
        Recycler_Libro.setHasFixedSize(true);
        Recycler_Libro.setAdapter(adapter);
        return viewLibroFragment;
    }
}

