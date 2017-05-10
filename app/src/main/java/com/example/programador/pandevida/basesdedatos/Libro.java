package com.example.programador.pandevida.basesdedatos;

/**
 * Created by HostSandro on 10/5/2017.
 */

public class Libro {

    private String Nombre;
    private int Capitulos;
    private String Osis;

    public Libro(String osis, String nombre, int capitulos) {
        Nombre = nombre;
        Capitulos = capitulos;
        Osis = osis;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCapitulos() {
        return Capitulos;
    }

    public void setCapitulos(int capitulos) {
        Capitulos = capitulos;
    }

    public String getOsis() {
        return Osis;
    }

    public void setOsis(String osis) {
        Osis = osis;
    }
}
