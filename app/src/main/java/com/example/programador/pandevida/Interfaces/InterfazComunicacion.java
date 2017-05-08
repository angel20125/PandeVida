package com.example.programador.pandevida.Interfaces;

import android.widget.TextView;

/**
 * Created by angel20125 on 09/04/17.
 */

//InterfazComunicacion  es la encargada de comunicar los fragmentos entre ellos
public interface InterfazComunicacion {

    public void IrACapitulo(String libro);

    public void IrAVersiculo(int capitulo);

    public void IrALectura(int versiculo);

}
