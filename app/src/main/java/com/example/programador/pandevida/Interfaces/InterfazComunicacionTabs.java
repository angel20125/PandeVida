package com.example.programador.pandevida.Interfaces;


/**
 * Created by angel20125 on 09/04/17.
 */

//InterfazComunicacionTabs  es la encargada de comunicar los fragmentos entre ellos
public interface InterfazComunicacionTabs {

    void IrACapitulo(String libro, int CantidadDeCapitulos);

    void IrAVersiculo(int capitulo);

    void IrALectura();

}
