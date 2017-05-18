package com.example.programador.pandevida.basesdedatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.programador.pandevida.Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

//import static com.example.programador.pandevida.Activities.MainActivity.biblia;

/**
 * Created by HostSandro on 10/5/2017.
 */

public class SQLiteManager {

    private static SQLiteManager instance;

    public static SQLiteManager getInstance(){

        if(instance==null){
         instance= new SQLiteManager();

        }
        return instance;
    }

    public List<Libro> getLibros(Context context){

        List<Libro> ListaLibros= new ArrayList<>();

        try {
            MyDataBase mdb = new MyDataBase(context.getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT osis, human, chapters FROM 'books_fts'", null);
            cursor.moveToFirst();
            Libro libro;
            if (cursor.moveToFirst()) {
                int i =-1;
                while (cursor.isAfterLast() == false) {
                    i++;
                    libro= new Libro(cursor.getString(0),cursor.getString(1),cursor.getInt(2));
                    ListaLibros.add(libro);
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
        return ListaLibros;
    }

    public int getVersiculos(Context context, int capitulo){
        int versiculos=0;
        try {
            MyDataBase mdb = new MyDataBase(context.getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT verse FROM 'bible_fts'"+
                    "WHERE book="+"'"+Biblia.getOsis()+"'"+
                    "AND chapter="+"'"+capitulo+"'",null);

            if(cursor.moveToFirst()){
                cursor.moveToLast();
                Log.v("VersiculoFragment", "El Ultimo verisculo es: "+cursor.getInt(0));
                versiculos = cursor.getInt(0);
            }else{
                Log.v("VersiculoFragment"," error en VersiculoFragment: no se pudo leer el ultimo registro en ");
            }
            db.close();
            cursor.close();
            mdb.close();
            mdb.finalize();
        }catch (Exception e){
            Log.v("VersiculoFragment","Error en la conexion de bases de datos VersiculoFragment");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return versiculos;
    }

    public List<String> getLectura(Context context){
        //Hago consulta a la BDD
        //Se pasa por parametro por aqui los libros de la Biblia que se saca de la bases de datos
        //Lleno la lista y se la paso al adapter (El adapter recibe Interfaz!)
        //Le pongo layut manager y adapter al Recycler View
        List<String> Versos = new ArrayList<>();
        try {
            MyDataBase mdb = new MyDataBase(context.getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM 'bible_fts'"+
                    "WHERE book="+"'"+Biblia.getOsis()+"'"+
                    "AND chapter="+"'"+Biblia.getCapitulo()+"'",null);

            if(cursor.moveToFirst()){

                int numeroVerso=1;
                while(cursor.isAfterLast()!=true){
                    Versos.add((numeroVerso+". "+cursor.getString(3)));
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
        }catch (Exception e){
            Log.v("LecturaFragment","Error en la conexcion de bases de datos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return Versos;
    }

    public String getNombreDeBiblia (Context context){

//ToDo Hace falta arreglar la BDD para que dé bien el nombre

        /*try {
            MyDataBase mdb = new MyDataBase(context.getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM 'bible_fts'"+
                    "WHERE book="+"'"+Biblia.getOsis()+"'"+
                    "AND chapter="+"'"+Biblia.getCapitulo()+"'",null);

            if(cursor.moveToFirst()){

                int numeroVerso=1;
                while(cursor.isAfterLast()!=true){
                    Versos.add((numeroVerso+". "+cursor.getString(3)));
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
            Log.v("LecturaFragment","Libro:    "+ Biblia.getLibro());
            Log.v("LecturaFragment","capitulo: "+ Biblia.getCapitulo());
            Log.v("LecturaFragment","verso: "   +Biblia.getVerso());
            Log.v("LecturaFragment", "---------------------------------");/*
            for (int i=0; i<=cantidadVersos.size()-1; i++){

                Log.v("LecturaFragment", " "+cantidadVersos.get(i));
            }
            Log.v("LecturaFragment", "---------------------------------");
            Log.v("LecturaFragment", " Tamaño: "+ cantidadVersos.size());*/
        /*}catch (Exception e){
            Log.v("LecturaFragment","Error en la conexcion de bases de datos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/
        return null;
    }

    /*public void ProximoCapitulo(Context context){

        try {
            MyDataBase mdb = new MyDataBase(context.getApplicationContext(), 0);
            SQLiteDatabase db = mdb.getWritableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("SELECT * FROM 'bible_fts'"+
                    "WHERE book="+"'"+Biblia.getOsis()+"'"+
                    "AND chapter="+"'"+Biblia.getCapitulo()+"'",null);

            if(cursor.moveToFirst()){

                int numeroVerso=1;
                while(cursor.isAfterLast()!=true){
                    Versos.add((numeroVerso+". "+cursor.getString(3)));
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
        }catch (Exception e){
            Log.v("LecturaFragment","Error en la conexcion de bases de datos");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }*/

}
