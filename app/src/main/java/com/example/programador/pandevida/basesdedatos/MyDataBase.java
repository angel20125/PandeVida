package com.example.programador.pandevida.basesdedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.programador.pandevida.Utils.Constantes;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Programador on 02/02/2017.
 */
//SQLiteAssetHelper
public class MyDataBase extends SQLiteAssetHelper {
    //private static String DATABASE_NAME= "rva.db";
    //private static  final  String DATABASE_PATH="C:\\Users\\Programador\\AndroidStudioProjects\\Biblia2\\app\\src\\main\\assets\\databases";

    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDataBase(Context context){

        super(context, Constantes.DATABASE_NAME,null, Constantes.DATABASE_VERSION);
    }
    // en el caso que se estas guardando  en lugar externo
     public  MyDataBase(Context context, int valor){
         super(context, Constantes.DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(),null, Constantes.DATABASE_VERSION);

     }

    @Override
    public void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

}
