package com.example.programador.pandevida.basesdedatos;



import android.app.Application;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by Programador on 07/02/2017.
 */
public final class  Biblia extends Application implements Serializable  {
    @SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings


    private static String libro;
    private static String osis;
    private static int capitulo;
    private static int verso;
    private static int versoFinal;
    private static int capituloFinal;
    private static String contenido;

    private ArrayList<String> contenidoCap=new ArrayList<String>();// posiblemente se use

    //----------------------METODOS DE LA BIBILIA-------------------------//
    public  Biblia (){

        libro="Génesis";
        setOsis("Gen");
        capitulo=1;
        verso=1;
        versoFinal=0;
        capituloFinal=0;
        contenido="";
    }

    public Biblia(String libro, String osis,int capitulo, int verso, String contenido) {

        this.libro = libro;
        this.setOsis(osis);
        this.capitulo = capitulo;
        this.verso = verso;
        this.contenido = contenido;
        this.versoFinal = 0;

    }
    //---------------------METODOS SET Y GET-------------------



    public static String getLibro() {
        return libro;
    }

    public static String getOsis() {
        return osis;
    }

    public static void setOsis(String osis) {
        Biblia.osis = osis;
    }

    public static void setLibro(String libro) {Biblia.libro = libro;}

    public static int getCapitulo() {return capitulo;}

    public static void setCapitulo(int capitulo) {
        Biblia.capitulo = capitulo;
    }

    public static int getVerso() {
        return verso;
    }

    public static void setVerso(int verso) {
        Biblia.verso = verso;
    }

    public static String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public static int getVersoFinal() {
        return versoFinal;
    }

    public void setVersoFinal(int versoFinal) {this.versoFinal = versoFinal;}

    public ArrayList<String> getContenidoCap() {
        return contenidoCap;
    }

    public void setContenidoCap(ArrayList<String> contenidoCap) {this.contenidoCap = contenidoCap;}

    public static int getCapituloFinal() {
        return capituloFinal;
    }

    public  void setCapituloFinal(int capituloFinal) {this.capituloFinal = capituloFinal;
    }


}
